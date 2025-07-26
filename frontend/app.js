function sendMessage() {
  const userInput = document.getElementById("userInput").value;

  fetch("http://localhost:8080/api/chat", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      conversationId: "abc123",
      userMessage: userInput
    })
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("responseBox").innerText = data.response;
  })
  .catch(error => {
    console.error("Error:", error);
    document.getElementById("responseBox").innerText = "Error connecting to backend.";
  });
}
