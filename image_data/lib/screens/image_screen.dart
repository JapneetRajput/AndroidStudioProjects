import 'dart:io';
import 'package:flutter/material.dart';
import 'package:camera/camera.dart';

class ImageScreen extends StatefulWidget {
  const ImageScreen({Key? key}) : super(key: key);

  @override
  _ImageScreenState createState() => _ImageScreenState();
}

class _ImageScreenState extends State<ImageScreen> {
  late CameraController _controller;
  late Future<void> _initializeControllerFuture;
  File? _capturedImage;

  @override
  void initState() {
    super.initState();
    _initializeControllerFuture = _initializeCamera();
  }

  Future<void> _initializeCamera() async {
    final cameras = await availableCameras();
    final firstCamera = cameras.first;

    _controller = CameraController(
      firstCamera,
      ResolutionPreset.medium,
    );

    return _controller.initialize();
  }

  void _takePicture() async {
    try {
      await _initializeControllerFuture;
      XFile? picture = await _controller.takePicture();
      setState(() {
        _capturedImage = File(picture!.path); // Store the captured image file
      });
    } catch (e) {
      print('Error: $e');
    }
  }

  void _reloadCamera() {
    setState(() {
      _capturedImage = null; // Clear the captured image
    });
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Image Page'),
      ),
      body: FutureBuilder<void>(
        future: _initializeControllerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            return _capturedImage != null
                ? Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(child: Image.file(_capturedImage!)),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    ElevatedButton(
                      onPressed: () => {}, // Perform your action
                      child: const Text('Save'),
                    ),
                    ElevatedButton(
                      onPressed: _reloadCamera,
                      child: const Text('Reload'),
                    ),
                  ],
                ),
              ],
            )
                : CameraPreview(_controller);
          } else {
            return const Center(child: CircularProgressIndicator());
          }
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _takePicture,
        child: const Icon(Icons.camera),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}
