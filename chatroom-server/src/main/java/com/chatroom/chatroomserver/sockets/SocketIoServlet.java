package com.chatroom.chatroomserver.sockets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		mEngineIoServer.handleRequest(req, resp);
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Hello, World!</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, World!</h1>");
        out.println("</body></html>");
	}
	public static EngineIoServer getMengineioserver() {
		return mEngineIoServer;
	}
	public static SocketIoServer getMsocketioserver() {
		return mSocketIoServer;
	}
	
	
	
    
    
    

}
