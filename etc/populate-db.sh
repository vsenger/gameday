


curl -X POST ${API_URL}/team -H 'Content-Type: application/json' -d '{"teamID": "Lovers", "description" : "love is all you need"}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000001", "category" : "Java Basic", "question": "Which Java version is recommended Today?", "answer1" : "java8", "answer2" : "java11", "answer3" : "java17",  "answer4": "java21", "correctAnswer" : 3}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000002", "category" : "Java History", "question": "What is the .class signature in the initial bytes?", "answer1" : "JAVAONE", "answer2" : "JAVA", "answer3" : "OAK",  "answer4": "CAFEBABE", "correctAnswer" : 4}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000003", "category" : "Java Basic", "question": "How many bytes a long variable consume?", "answer1" : "1", "answer2" : "4", "answer3" : "8",  "answer4": "16", "correctAnswer" : 3}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000004", "category" : "Java Basic", "question": "How many bytes a double variable consume?", "answer1" : "2", "answer2" : "4", "answer3" : "8",  "answer4": "depends", "correctAnswer" : 3}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000005", "category" : "Java History", "question": "Which software was not developed with Java?", "answer1" : "Arduino IDE", "answer2" : "Processing.org", "answer3" : "Netbeans",  "answer4": "Openoffice", "correctAnswer" : 4}'

curl -X POST ${API_URL}/quiz -H 'Content-Type: application/json' -d '{"questionID": "0000006", "category" : "Java History", "question": "Which AWS service helps you to write Java code with genAI?", "answer1" : "AWS Lambda", "answer2" : "Amazon CodeWhisperer", "answer3" : "Amazon Cloud9",  "answer4": "Amazon Java Editor", "correctAnswer" : 2}'

