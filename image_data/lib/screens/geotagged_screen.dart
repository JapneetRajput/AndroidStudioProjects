import 'dart:io';
import 'package:flutter/material.dart';
import 'package:camera/camera.dart';
import 'package:geolocator/geolocator.dart';
import 'package:path_provider/path_provider.dart';
import 'package:csv/csv.dart';

class GeotaggedScreen extends StatefulWidget {
  const GeotaggedScreen({Key? key}) : super(key: key);

  @override
  _GeotaggedScreenState createState() => _GeotaggedScreenState();
}

class _GeotaggedScreenState extends State<GeotaggedScreen> {
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

  Future<void> _takePicture() async {
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

  Future<void> _saveImageAndCSV() async {
    if (_capturedImage != null) {
      // Save the captured image to app's internal storage
      await _saveImageToInternalStorage(_capturedImage!.path);

      // Get current location
      Position position = await _getCurrentLocation();
      double latitude = position.latitude;
      double longitude = position.longitude;

      // Save metadata to CSV file in app's internal storage
      await _saveMetadataToCSV(latitude, longitude, _capturedImage!.path);
    }
  }

  Future<void> _saveImageToInternalStorage(String imagePath) async {
    Directory appDocDir = await getApplicationDocumentsDirectory();
    String destinationPath = '${appDocDir.path}/captured_image.png';
    File sourceFile = File(imagePath);
    File destinationFile = File(destinationPath);

    try {
      // Copy the file from the source to the app's internal storage
      await sourceFile.copy(destinationPath);
      print('Image saved to internal storage: $destinationPath');
    } catch (e) {
      print('Error copying image to internal storage: $e');
    }
  }

  Future<void> _saveMetadataToCSV(double latitude, double longitude, String imagePath) async {
    Directory directory = await getApplicationDocumentsDirectory();
    String csvFilePath = '${directory.path}/your_csv_file.csv';
    File csvFile = File(csvFilePath);

    List<List<dynamic>> csvData = [];

    // Add headers to the CSV data list
    csvData.add(['Timestamp', 'Latitude', 'Longitude', 'ImageName']);

    // Add the new data to the CSV data list
    csvData.add([
      DateTime.now().toString(),
      latitude,
      longitude,
      imagePath,
    ]);

    // Write the data to the CSV file
    String csvContent = const ListToCsvConverter().convert(csvData);
    csvFile.writeAsStringSync(csvContent, mode: FileMode.writeOnly);
    print('New CSV file created at: $csvFilePath');
  }

  Future<Position> _getCurrentLocation() async {
    // Fetch the current position
    return await Geolocator.getCurrentPosition();
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
        title: const Text('Geotagged Image Page'),
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
                      onPressed: _saveImageAndCSV,
                      child: const Text('Save Image and CSV'),
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
