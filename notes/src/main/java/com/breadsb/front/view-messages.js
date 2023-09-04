$(document).ready(function () {

    var apiRoot = "http://localhost:8080/api/notes/";
    var getButton = $('[name="getAllMessages"]');
    var messagesList = $('[data-messages-list]');
    var itemsPerPage = 10;
    var getBodyText = 'Get body';

    function getMessageBodyButton(button, messageBody) {
        var closeBodyText = 'Close body';
        
        if (button.text() == getBodyText) {
            button.text(closeBodyText);
        } else {
            button.text(getBodyText);
        }
        
        $('.messageBody').hide();
        messageBody.slideToggle(1000);
    };

    function createMessage(element) {
        var message = $('<li>').addClass('message');
        var messageHint = $('<div>').addClass('message-hint');
        var messageTitle = $('<div>').addClass('messageTitleBlock block').text(element.title);
        var messageDate = $('<div>').addClass('messageDateBlock block').text(element.createdAt);
        var messageButton = $('<div>').addClass('messageButtonBlock');
        var messageBody = $('<div>').addClass('messageBody scrollbar').text(element.body);
        var getBodyButton = $('<button>').addClass('getMessageBodyButton messageButton').text(getBodyText).on('click', function () {
            getMessageBodyButton(getBodyButton, messageBody);
        });
        var updateMessageButton = $('<button>').addClass('updateMessageButton messageButton').text('Update').on('click', function() {
            updateMessage(updateMessageButton);
        });
        var deleteMessageButton = $('<button>').addClass('deleteMessageButton messageButton').text('Delete').on('click', function() {
            deleteMessage;
        })
        
        messageButton.append(getBodyButton);
        var buttonsBlock = $('<div>').addClass('restButtons');
        buttonsBlock.append(updateMessageButton);
        buttonsBlock.append(deleteMessageButton);
        messageButton.append(buttonsBlock)
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
