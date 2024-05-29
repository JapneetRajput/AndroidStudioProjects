import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  var answer ;
  String? valueChosen='Item 1';
  final List items = [
    'Item 1', 'Item 2', 'Item 3', 'Item 4','Item 5'
  ];
  TextEditingController valueController = TextEditingController();

  void calculator(){
    setState(() {
      // var input = valueController.text as int;
      var input = int.parse(valueController.text);

      answer = input.toRadixString(2);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Colors.white,
          elevation: 0,
          title: const Text(
            'Binary',
            style: TextStyle(color: Colors.black),
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.all(30.0),
          child: Column(
            children: [
              Container(
                child: TextField(
                  controller: valueController,
                  style:const TextStyle(
                    fontSize: 20,
                  ),
                  keyboardType: TextInputType.number,
                  decoration: const InputDecoration(
                    labelText: 'Value',
                    labelStyle: TextStyle(
                      fontSize: 20,
                    ),
                    border: OutlineInputBorder(),
                  ),
                ),
              padding: const EdgeInsets.all(32),
              //   height:120,
              //   decoration: BoxDecoration(
              //       color: Colors.white,
              //       borderRadius: BorderRadius.circular(5),
              //       boxShadow: [
              //         BoxShadow(
              //             color: Colors.indigo.withOpacity(0.3),
              //             spreadRadius: 2,
              //             blurRadius: 4,
              //             offset: Offset(0,3))
              //       ]),
              ),
              DropdownButton<String>(
                value: valueChosen,
                items:items.map((item)=> DropdownMenuItem<String>(
                  value: item,
                  child: Text(item,style: TextStyle(fontSize: 24)))).toList(),
                onChanged: (item){
                  setState(() {
                    valueChosen=item.toString();
                  });
                  },
                ),
              RaisedButton(
                child: Text('Calculate'),
                onPressed:
                    calculator,
              ),
              Text(
                '$answer',
              ),
            ],
          ),
        ),
    );
  }
}
