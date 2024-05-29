import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
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

  late StreamSubscription<Position> _locationSubscription;
  late int _hitsCount = 0;

  void _startSendingRequests() {
    _locationSubscription = Geolocator.getPositionStream().listen((position) {
      _sendLocationToServer(position.latitude, position.longitude);
      _hitsCount++;
      setState(() {
        locationMessage = 'Latitude : ${position.latitude}, Longitude : ${position.longitude}';
      });
    });
  }

  void _stopSendingRequests() {
    _locationSubscription.cancel();
  }

  Future<void> _sendLocationToServer(double latitude, double longitude) async {
    final url = Uri.parse('http://40.81.232.173:3001/testserver?lat=${latitude.toString()}&lon=${longitude.toString()}'); // Replace with your server endpoint

    final response = await http.get(url);
    if (response.statusCode == 200) {
      print('Location sent successfully');
    } else {
      print('Failed to send location. Error: ${response.statusCode}');
      print(response.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(locationMessage),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _startSendingRequests,
              child: const Text("Start"),
            ),
            ElevatedButton(
              onPressed: _stopSendingRequests,
              child: const Text("Stop"),
            ),
            const SizedBox(height: 20),
            Text('Hits Count: $_hitsCount'),
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
