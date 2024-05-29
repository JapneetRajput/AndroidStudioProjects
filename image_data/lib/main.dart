import 'package:flutter/material.dart';
import 'package:image_data/screens/geotagged_screen.dart';
import 'package:image_data/screens/image_screen.dart';
import 'package:image_data/screens/location_screen.dart';
import 'screens/home_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Button Navigation Example',
      initialRoute: '/',
      routes: {
        '/': (context) => const HomeScreen(),
        '/location': (context) => const LocationScreen(),
        '/image': (context) => const ImageScreen(),
        '/geotagged': (context) => const GeotaggedScreen(),
      },
    );
  }
}
