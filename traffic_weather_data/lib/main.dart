import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart' as http;
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String locationMessage = 'Your location is : ';
  late String lat;
  late String long;

  late int _hitsCount = 0;
  late Timer _timer;
  bool _isSendingRequests = false;
  bool anomaly = false;

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  void _toggleSendingRequests() {
    if (_isSendingRequests) {
      _stopSendingRequests();
    } else {
      _startSendingRequests();
    }
  }

  void _startSendingRequests() async {
    var permissionStatus = await Permission.location.status;
    if (permissionStatus.isGranted) {
      _hitsCount = 0; // Reset hits count
      setState(() {
        _isSendingRequests = true;
      });

      // Call _sendLocationToServer immediately and then setup a timer for periodic updates
      _sendLocationPeriodically();
    } else {
      // If location permission is not granted, request it
      await _requestLocationPermission();
    }
  }

  void _stopSendingRequests() {
    _timer.cancel();
    setState(() {
      _isSendingRequests = false;
    });
  }

  void _changeAnomaly() {
    anomaly = !anomaly;
  }

  void _sendLocationPeriodically() {
    // Immediately send the first location
    _getCurrentLocationAndSend();

    // Setup a timer for periodic location updates
    _timer = Timer.periodic(const Duration(milliseconds: 200), (timer) {
      _getCurrentLocationAndSend();
    });
  }

  Future<void> _requestLocationPermission() async {
    var status = await Permission.location.request();
    if (status.isGranted) {
      _startSendingRequests();
    } else {
      print('Location permission denied');
    }
  }

  void _getCurrentLocationAndSend() {
    Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.best)
        .then((position) {
      _sendLocationToServer(position.latitude, position.longitude);
      _hitsCount++;
      setState(() {
        locationMessage =
        'Latitude : ${position.latitude}, Longitude : ${position.longitude}';
      });
    }).catchError((e) {
      print("Error getting location: $e");
    });
  }

  Future<void> _sendLocationToServer(double latitude, double longitude) async {
    final url = Uri.parse(
        'http://98.70.76.100:3001/testserver?refName=flutteranurag&lat=${latitude.toString()}&lon=${longitude.toString()}&anomaly=${anomaly.toString()}'); // Replace with your server endpoint

    http.get(url);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Traffic and Weather data collector"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(locationMessage),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _toggleSendingRequests,
              child: Text(_isSendingRequests ? "Stop" : "Start"),
            ),
            const SizedBox(height: 20),
            Text('Hits Count: $_hitsCount'),
            const SizedBox(height: 40),
            ElevatedButton(
              onPressed: _changeAnomaly,
              child: Text("Mark anomaly as " + (!anomaly).toString()),
            ),
          ],
        ),
      ),
    );
  }
}
