$(document).ready(function () {

    var apiRoot = "http://localhost:8080/api/notes/";
    var getButton = $('[name="getAllMessages"]');
    var messagesList = $('[data-messages-list]');
    var itemsPerPage = 10;
    var getBodyText = 'Get body';

    function getMessageBodyButton(button, message) {
        var closeBodyText = 'Close body';
        console.log(button.text());
        
        if (button.text() == getBodyText) {
            button.text(closeBodyText);
        } else {
            button.text(getBodyText);
        }

        message.slideToggle(1000);
    };

    function createMessage(element) {
        var message = $('<li>').addClass('message');
        var messageHint = $('<div>').addClass('message-hint');
        var messageTitle = $('<div>').addClass('messageTitleBlock').text(element.title);
        var messageDate = $('<div>').addClass('messageDateBlock').text(element.createdAt);
        var messageButton = $('<div>').addClass('messageButtonBlock');
        var messageBody = $('<div>').addClass('messageBody').text(element.body);
        var getBodyButton = $('<button>').addClass('getMessageBodyButton').text(getBodyText).on('click', function () {
            getMessageBodyButton(getBodyButton, messageBody);
        });

        messageButton.append(getBodyButton);
        messageHint.append(messageTitle, messageDate, messageButton);
        message.append(messageHint, messageBody);

        return message;
    }

    function getMessages(messages) {
        $('#paginationMenu').pagination({
            dataSource: messages,
            pageSize: 10,
            callback: function (data, pagination) {
                displayData(data);
            },
            activeClassName: "activePage"
        })
        messagesList.removeClass('hidden');
        messagesList.slideToggle(1000);
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
