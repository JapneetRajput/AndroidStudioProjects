import 'dart:js';

import 'package:flutter/material.dart';
import 'package:world_time/pages/choose_location.dart';
import 'package:world_time/pages/home.dart';
import 'package:world_time/pages/loading.dart';
import 'package:world_time/pages/choose_location.dart';

void main() {
  runApp(MaterialApp(
    initialRoute: '/',
    routes: {
      '/home' : (context) => Home(),
      '/' : (context) => Loading(),
      '/location' : (context) => ChooseLocation(),
    },
  ));
}
