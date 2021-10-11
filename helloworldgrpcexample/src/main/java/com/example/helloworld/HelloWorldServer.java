package com.example.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.example.helloworld.GreeterGrpc.GreeterImplBase;;

public class HelloWorldServer extends GreeterImplBase {
	
	public static void main(String [] args) {
		
		int port = 50051;		//needs to connect on the same port as the client
		
		HelloWorldServer helloworldserver = new HelloWorldServer();
		
		try {
			Server server = ServerBuilder.forPort(port)
					.addService(helloworldserver)
					.build()
					.start();
			
			server.awaitTermination();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//want to implement our sayHello method
	//start typing the sayHello and click ctrl and space and it will implement the interface method. Remove the super sayHello.Need to give back a response
	
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		System.out.println("You called the server");
		
		HelloReply reply = HelloReply.newBuilder()
				.setMessage("Hello" + request.getName()).build();		//the getter of the name object that we definited in the service definition
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

}
