import 'package:http/http.dart';
import 'dart:convert';
import 'package:intl/intl.dart';

class WorldTime {

  late String location;
  late String time;
  late String flag;
  late String url;

  WorldTime({required this.location,required this.flag,required this.url});

  Future<void> getTime() async{

    try {
      Response response = await get(
          Uri.parse('http://worldtimeapi.org/api/timezone/$url'));
      Map data = jsonDecode(response.body);
      // print(data);
      // print(data['utc_offset']);
      String dateTime = data['datetime'];
      String offsetHour = data['utc_offset'].substring(1, 3);
      String offsetMinute = data['utc_offset'].substring(4, 6);
      // print(offsetMinute);

      DateTime now = DateTime.parse(dateTime);
      now = now.add(Duration(
          hours: int.parse(offsetHour), minutes: int.parse(offsetMinute)));
      // print(now);

      time = DateFormat.jm().format(now);
    }
    catch(e){
      time='couldnt get time data';
    }
  }


}