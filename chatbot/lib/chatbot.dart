import 'dart:convert';

import 'package:dash_chat_2/dash_chat_2.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Chatbot extends StatefulWidget {
  const Chatbot({super.key});

  @override
  State<Chatbot> createState() => _ChatbotState();
}

class _ChatbotState extends State<Chatbot> {

  ChatUser myself=ChatUser(id: "1",firstName:"Arjun");
  ChatUser bot=ChatUser(id: "2",firstName:"Gemini AI");

  List<ChatMessage> allMessages=[];
  List<ChatUser> typing = [];

  final geminiUrl = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=AIzaSyAhlaWdtv__3QhH8bL-kD4dEN0Mrrvn1Ts';

  final header = {
    'Content-Type': 'application/json'
  };

  getData(ChatMessage m) async {
    typing.add(bot);
    allMessages.insert(0, m);
    setState(() {
    });

    var data = {"contents":[{"parts":[{"text":m.text}]}]};

    print(data);
    await http.post(Uri.parse(geminiUrl),headers:header, body:jsonEncode(data))
      .then((value){
        if(value.statusCode==200){
          var result = jsonDecode(value.body);
          print(result['candidates'][0]['content']['parts'][0]['text']);
          
          ChatMessage m1 = ChatMessage(
              text:result['candidates'][0]['content']['parts'][0]['text'],
              user: bot,
              createdAt: DateTime.now());

          allMessages.insert(0, m1);
          setState(() {

          });
        }
    }).catchError((e){
      print(e);
    });
    typing.remove(bot);
    setState(() {

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: DashChat(
        typingUsers: typing,
        currentUser: myself,
        onSend: (ChatMessage m){
          getData(m);
        },
        messages: allMessages,
      ),
    );
  }
}
