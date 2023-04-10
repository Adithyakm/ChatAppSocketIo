//<script src="http://yourwebsite.com:12345/socket.io/socket.io.js"></script>
//const io = require("socket.io-client");
const socket = io("/socketio");

function connect(){
	/*let socket = new SockJS("/server")
	
	//stompClient = Stomp.over(socket)
	
	stompClient.connect({},function(frame){
		console.log("Connected : "+frame)
		
		$("#name-from").addClass('d-none')
		$("#chat-room").removeClass('d-none')
		
		//subscribe
		stompClient.subscribe("/topic/return-to",function(response){
			showMessage(JSON.parse(response.body))
		})
	})*/
	
	socket.on("connection",()=>{
		socket.on("connect_error", (err) => {
  console.log(`connect_error due to ${err.message}`);
        });
		console.log(socket.connected);
		$("#name-from").addClass('d-none')
		$("#chat-room").removeClass('d-none')
		socket.join("room");
		socket.on("message",(arg)=>{
			showMessage(arg);
		})
	})
}

function showMessage(message){
	
	$("#message-container-table").prepend(`<tr><td><b>${message.messageSender} : </b> ${message.messageContent}</td></tr>`)
	
}

function sendMessage(){
	let jsonOb={
		messageSender : localStorage.getItem("name"),
		messageContent : $("#message-value").val()
	}
	
	//stompClient.send("/app/message",{},JSON.stringify(jsonOb));
	socket.emit("message",jsonOb);
}


$(document).ready(e=>{
	
	$("#login").click(()=>{
		
		let name = $("#name-value").val()
		
		localStorage.setItem("name",name)
		
		connect();
	
	})
	
	$("#send-btn").click(()=>{
		sendMessage();
	})
	
	$("#logout").click(()=>{
		localStorage.removeItem("name")
		/*if(stompClient!==null){
			stompClient.disconnect()
			$("#name-from").removeClass('d-none')
			$("#chat-room").addClass('d-none')
		}*/
		
		socket.on("disconnect",()=>{
			("#name-from").removeClass('d-none')
			$("#chat-room").addClass('d-none')
		})
	})

}
)
