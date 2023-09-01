$(document).ready(function () {

    var apiRoot = "http://localhost:8080/api/notes/";
    var getButton = $('[name="getAllMessages"]');
    var messagesList = $('[data-messages-list]');

    var getMessageBodyButton = $('.getMessageBodyButton').on('click', function () {
        $('.messageBody').slideToggle("slow");
    })

    function createMessage(element) {
        var message = $('<li>').addClass('message');
        var messageHint = $('<div>').addClass('message-hint');
        var messageTitle = $('<div>').addClass('messageTitleBlock').text(element.title);
        var messageDate = $('<div>').addClass('messageDateBlock').text(element.createdAt);
        var messageButton = $('<div>').addClass('messageButtonBlock');
        var messageBody = $('<div>').addClass('messageBody').text(element.body);
        var getBodyButton = $('<button>').addClass('getMessageBodyButton').text('Get Body').on('click', function () {
            messageBody.slideToggle();
        });

        messageButton.append(getBodyButton);
        messageHint.append(messageTitle, messageDate, messageButton);
        message.append(messageHint, messageBody);

        return message;
    }

    function getMessages(data) {
        messagesList.empty();
        data.forEach( function(element) {
            createMessage(element).appendTo(messagesList);
        });
    }

    function getAllMessages(event) {
        var connectionURL = apiRoot;

        $.ajax({
            url: connectionURL,
            method: "GET",
            success: getMessages
        });
    }

    getButton.on("click", getAllMessages);
});
