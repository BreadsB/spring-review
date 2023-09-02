$(document).ready(function () {

    var apiRoot = "http://localhost:8080/api/notes/";
    var getButton = $('[name="getAllMessages"]');
    var messagesList = $('[data-messages-list]');
    var itemsPerPage = 10;

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

    function getMessages(messages) {
        messagesList.empty();
        
        $('#paginationMenu').pagination({
            dataSource: messages,
            pageSize: 10,
            callback: function(data, pagination) {
                displayData(data);
            }
        })
    }
    
    function displayData(data) {
        messagesList.empty();
        data.forEach(function (element) {
            createMessage(element).appendTo(messagesList);
        });
    }

    function getAllMessages(event) {
        var connectionURL = apiRoot;

        $.ajax({
            url: connectionURL,
            method: "GET",
            success: getMessages,
            error: function () {
                alert("Error fetching data from the server");
            }
        });
    }

    getButton.on("click", getAllMessages);
});
