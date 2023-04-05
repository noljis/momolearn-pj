let subUrl = "http://localhost/momolearn/sub";

$(document).ready(function(){
	const members = $('#session-members').val();
	const memId = $('#session-memId').val();
	if(members!= null){
		let userId = memId;
		let eventSource = new EventSource(subUrl + "?userId=" + userId);
		
		eventSource.addEventListener("writeComment", function(event){
			let message = event.data;
			showMessage(message);
			
		})
		
		eventSource.addEventListener("likePost", function(event){
			let message = event.data;
			showMessage(message);
			
		})
		
		eventSource.addEventListener("error", function(event){
			eventSource.close();
		})
	}
})

function showMessage(message) {
  const messageBox = document.querySelector('#message');
  messageBox.textContent = message;
  messageBox.classList.add('active');
  setTimeout(() => {
    messageBox.classList.remove('active');
  }, 3000);
}