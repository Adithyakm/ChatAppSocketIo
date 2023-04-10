package com.chatroom.chatroomserver.sockets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;

import io.socket.engineio.server.EngineIoServer;
import io.socket.socketio.server.SocketIoServer;


@WebServlet(name="Servlet",urlPatterns="/socketio")
public class SocketIoServlet extends HttpServlet {
	
	private static final EngineIoServer mEngineIoServer = new EngineIoServer();
    private static final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//resp.addHeader("Access-Control-Allow-Origin", "http://localhost:9898/");
		//resp.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		System.out.println("server started on "+req.getLocalPort());
		mEngineIoServer.handleRequest(req, resp);
	}
	public static EngineIoServer getMengineioserver() {
		return mEngineIoServer;
	}
	public static SocketIoServer getMsocketioserver() {
		return mSocketIoServer;
	}
	
	
	
    
    
    

}
