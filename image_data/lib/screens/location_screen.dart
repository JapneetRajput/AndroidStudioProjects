import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

class LocationScreen extends StatefulWidget {
  const LocationScreen({Key? key}) : super(key: key);

  @override
  _LocationScreenState createState() => _LocationScreenState();
}

class _LocationScreenState extends State<LocationScreen> {
  late Future<Position> _currentLocation;

  Future<Position> _getCurrentLocation() async {
    // Fetch the current position
    return await Geolocator.getCurrentPosition();
  }

  @override
  void initState() {
    super.initState();
    _currentLocation = _getCurrentLocation();
  }

  void _fetchLocation() {
    setState(() {
      _currentLocation = _getCurrentLocation();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Location Page'),
      ),
      body: Center(
        child: FutureBuilder<Position>(
          future: _currentLocation,
          builder: (context, snapshot) {
            if (snapshot.hasError) {
              return Text('Error: ${snapshot.error}');
            }

            if (snapshot.connectionState == ConnectionState.waiting) {
              return const CircularProgressIndicator();
            }

            double latitude = snapshot.data!.latitude;
            double longitude = snapshot.data!.longitude;

            return Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  'Latitude: $latitude',
                  style: const TextStyle(fontSize: 24),
                ),
                Text(
                  'Longitude: $longitude',
                  style: const TextStyle(fontSize: 24),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _fetchLocation,
                  child: const Text('Refetch Location'),
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
