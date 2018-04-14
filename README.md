# QueueSender
It demonstrates inter-process communication using queues.

This application requires the QueueReceiver application to demonstrate putting info on mq and reading from the mq in multiple processes.

This Application's functionality is to read the files present in the filePath location and copy then to processed sub-directory, along with sending the messages to the mq.

### Features
  - read the file from the filepath location.
  - send the processed messages to queue.

### To Run
You will need:
- JDK 1.8
- maven

1.Run the QueueReceiverApplication(as many processes required) from QueueReceiver application
2.Run the QueueSenderApplication(as many processes required) from this application.
3.Place files in the filepath location. Once put on queue, the files will be copied to the processed sub-directory and processed by the ReaderApplication processes. 

### TO-Do
- change the filePath location in QueueSenderApplication.

### Testing
Run the QueueReceiverApplication(as many processes required), then run the Junit Test Case QueueSenderApplicationTest.java. The messages would be received by all the receivers from the queue.
