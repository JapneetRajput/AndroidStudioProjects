import 'package:flutter/material.dart';
import 'package:camera/camera.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: CameraScreen(),
    );
  }
}

class CameraScreen extends StatefulWidget {
  @override
  _CameraScreenState createState() => _CameraScreenState();
}

class _CameraScreenState extends State<CameraScreen> {
  late CameraController _controller;
  late Future<void> _initializeControllerFuture;
  TextEditingController filenameController = TextEditingController();

  @override
  void initState() {
    super.initState();

    // Initialize the camera
    _initializeCamera();
  }

  Future<void> _initializeCamera() async {
    final cameras = await availableCameras();
    final firstCamera = cameras.first;

    _controller = CameraController(
      firstCamera,
      ResolutionPreset.medium,
    );

    _initializeControllerFuture = _controller.initialize();
  }

  @override
  void dispose() {
    // Dispose of the controller when the widget is disposed
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Camera App'),
      ),
      body: Center(
        child: _buildCameraView(),
      ),
    );
  }

  Widget _buildCameraView() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        TextFormField(
          controller: filenameController,
          decoration: InputDecoration(
            labelText: 'Enter Filename',
            border: OutlineInputBorder(),
          ),
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            await _initializeControllerFuture;
            setState(() {});
          },
          child: Text('Start Camera'),
        ),
        SizedBox(height: 20),
        _controller.value.isInitialized
            ? Column(
          children: <Widget>[
            SizedBox(
              width: 300,
              height: 300,
              child: CameraPreview(_controller),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                try {
                  // Ensure the camera is initialized
                  await _initializeControllerFuture;

                  // Take the picture
                  XFile picture = await _controller.takePicture();

                  // Save the picture to gallery or do something with it
                  // For example, you can save it using the path and filename entered by the user
                  String fileName = filenameController.text;
                  // Handle saving the picture with the entered filename
                  // ...

                  print('Picture taken: ${picture.path}');
                } catch (e) {
                  print('Error taking picture: $e');
                }
              },
              child: Text('Capture Image'),
            ),
          ],
        )
            : Container(),
      ],
    );
  }
}
