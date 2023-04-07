package com.chatroom.chatroomserver;

import com.chatroom.chatroomserver.Models.Messaging;
import com.chatroom.chatroomserver.sockets.SocketIoServlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.socket.engineio.server.Emitter;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;

public class ChatRoomApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   SocketIoServer server = SocketIoServlet.getMsocketioserver();
	   SocketIoNamespace nameSpace = server.namespace("/");
	   Messaging broadcastMessage = null;
	   nameSpace.on("connection", new Emitter.Listener() {
		
		@Override
		public void call(Object... args) {
			// TODO Auto-generated method stub
			SocketIoSocket socket = (SocketIoSocket) args[0];
			System.out.println("Client " + socket.getId() + " (" + socket.getInitialHeaders().get("remote_addr") + ") has connected.");
			socket.on("message", new Emitter.Listener() {
				
				@Override
				public void call(Object... args) {
					ObjectMapper mapper = new ObjectMapper();
				    String json = (String)args[0];
				    Messaging messageDetails;
					try {
						messageDetails = mapper.readValue(json,Messaging.class);
						Messaging message = MessageDB.saveMessage(messageDetails);
					    socket.send("message",message);
					    nameSpace.broadcast("room","message",broadcastMessage);
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} );
			
		}

	});
  }

}
