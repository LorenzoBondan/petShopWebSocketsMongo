'use strict';

var chatPage = document.querySelector('#chat-page');
var messageInput = document.querySelector('#message');

var buttonSend = document.querySelector('#sendButton');

var stompClient = null;
var username = null;

//----------------------

function click(event){ // button
    console.log("Entrou no click");
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onClickButton, onError);
    event.preventDefault();
}

function onClickButton(){ // button
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({message: 'clicked in the button'})); // payload do controller
}

function clickTextBox(event){ // textbox
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onClickTextBox, onError);
    event.preventDefault();
}

function onClickTextBox(){ // textbox
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({sender: username, type: 'SYSTEM', content: `${username} has clicked the textbox`}))
    connectingElement.classList.add('hidden');
}

function checkCheckBox(event){ // checkbox
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onCheckCheckBox, onError);
    event.preventDefault();
}

function onCheckCheckBox(){ // checkbox
    var checkbox = document.getElementById("messageCheckbox");
    var isChecked = checkbox.checked;
    if (isChecked) {
        stompClient.subscribe('/topic/public');
        stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({sender: username, type: 'SYSTEM', content: `${username} has checked the checkbox`}))
        connectingElement.classList.add('hidden');
    }
    else{
        stompClient.subscribe('/topic/public');
        stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({sender: username, type: 'SYSTEM', content: `${username} has unchecked the checkbox`}))
        connectingElement.classList.add('hidden');
    }
}

function changeSelectItem(event){ // select (combobox)
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onChangeSelectItem, onError);
    event.preventDefault();
}

function onChangeSelectItem(){ // select (combobox)
    var select = document.getElementById("messageSelect");
    var selectedItem = select.value;
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({sender: username, type: 'SYSTEM', content: `${username} has changed the select item to ${selectedItem}`}))
    connectingElement.classList.add('hidden');
}

function mouseHoverLabel(event){
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onMouseHoverLabel, onError);
    event.preventDefault();
}

function onMouseHoverLabel(){
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/chat.componentClicked", {}, JSON.stringify({sender: username, type: 'SYSTEM', content: `${username} has hovered the mouse over the label`}))
    connectingElement.classList.add('hidden');
}

//-------------------

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


///-------------
messageButton.addEventListener('click', click, true)
//messageInput.addEventListener('click', clickTextBox, true)
messageCheckbox.addEventListener('change', checkCheckBox, true)
messageSelect.addEventListener('change', changeSelectItem, true)
messageLabel.addEventListener('mouseenter', mouseHoverLabel, true)


