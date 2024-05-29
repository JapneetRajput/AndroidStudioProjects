import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, '/location');
              },
              child: const Text('Location'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, '/image');
              },
              child: const Text('Image'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, '/geotagged');
              },
              child: const Text('Geotagged'),
            ),
          ],
        ),
      ),
    );
  }
}
