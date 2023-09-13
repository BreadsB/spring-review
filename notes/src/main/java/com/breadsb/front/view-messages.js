$(document).ready(function () {

    var apiRoot = "http://localhost:8080/api/notes/";
    var getButton = $('[name="getAllMessages"]');
    var messagesList = $('[data-messages-list]');
    var itemsPerPage = 10;
    var getBodyText = 'Get body';
    var sortingBlock = $('[_sortingBlock]');
    var datePicker = $('[_datePicker]');
    var filterButton = $('[_dateFilter]');

    function getMessageBodyButton(button, messageBody) {
        var closeBodyText = 'Close body';

        if (button.text() == getBodyText) {
            button.text(closeBodyText);
        } else {
            button.text(getBodyText);
        }

        messageBody.slideToggle(1000);
    };

    function createMessage(element) {
        var message = $('<li>').addClass('message');
        var messageHint = $('<div>').addClass('message-hint');
        var messageTitle = $('<div>').addClass('messageTitleBlock block').text(element.title);

        var dc = new Date(element.createdAt);
        var messageDate = $('<div>')
            .addClass('messageDateBlock block')
            .text(
                dc.getHours() + ":" +
                dc.getMinutes() + ":" +
                dc.getSeconds() + "." +
                dc.getMilliseconds() + "  " +
                dc.getDate() + "/" +
                (dc.getMonth() + 1) + "/" +
                dc.getFullYear()
            );

        var messageButton = $('<div>').addClass('messageButtonBlock');
        var messageBody = $('<div>').addClass('messageBody scrollbar').text(element.body);
        var getBodyButton = $('<button>').addClass('getMessageBodyButton messageButton').text(getBodyText).on('click', function () {
            getMessageBodyButton(getBodyButton, messageBody);
        });
        var updateMessageButton = $('<button>').addClass('updateMessageButton messageButton').text('Update').on('click', function () {
            updateMessage(updateMessageButton, messageTitle, messageBody, element.id);
        });
        var deleteMessageButton = $('<button>').addClass('deleteMessageButton messageButton').text('Delete').on('click', function () {
            confirmDelete(message, element.id);
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
        sortingBlock.removeClass('hidden');
        datePicker.removeClass('hidden');
        filterButton.removeClass('hidden');
        messagesList.slideDown(1000);
    }

    function displayData(data) {
        messagesList.empty();
        data.forEach(function (element) {
            createMessage(element).appendTo(messagesList);
        });

    }

    function updateMessage(button, title, body, id) {
        body.slideDown();

        var connectionURL = apiRoot;
        var titleText = title.text();
        var bodyText = body.text();
        var messageId = id;

        var titleInput = $('<input>').addClass('titleInputBlock block ').val(titleText);
        title.text('');
        title.append(titleInput);
        title.addClass('white');

        var bodyInput = $('<textarea>').addClass('bodyInputBlock block').val(bodyText);
        body.text('');
        body.append(bodyInput);
        body.addClass('white');

        button.on('click', function () {
            var newTitle = titleInput.val();
            var newBody = bodyInput.val();

            $.ajax({
                url: connectionURL + messageId,
                method: 'PUT',
                processData: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    "title": newTitle,
                    "body": newBody
                }),
                success: function (response) {
                    replaceInputWithText(titleInput, bodyInput, title, body);
                    afterUpdate(button);
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.log("Error at updating: " + textStatus + ", " + errorThrown);
                    console.log("Response Text:", xhr.responseText);
                }
            });
        })
    }

    function afterUpdate(button) {
        button.on('click', updateMessage);
        getAllMessages();
    }

    function replaceInputWithText(titleInput, bodyInput, title, body) {
        title.text(titleInput.val());
        body.text(bodyInput.val());
        titleInput.remove();
        bodyInput.remove();
        title.removeClass('white');
        body.removeClass('white');
    }

    function confirmDelete(message, messageId) {
        var connectionURL = apiRoot;
        var deleteText = "Are you sure you want to delete message?";
        $('<div></div>').appendTo('body')
            .html('<div><h6>' + deleteText + "</h6></div>")
            .dialog({
                modal: true,
                title: 'Delete message',
                zIndex: 1000,
                autoOpen: true,
                width: 'auto',
                resizable: false,
                buttons: {
                    Yes: function () {
                        $.ajax({
                            url: connectionURL + messageId,
                            method: 'DELETE',
                            success: function () {
                                console.log("Message has been deleted");
                                message.remove();
                                getAllMessages();
                            },
                            error: function () {
                                console.log("error at deleting");
                            }
                        });
                        $(this).dialog("close");
                    },
                    No: function () {
                        $(this).dialog("close");
                    }
                }
            });
    }

    function getAllMessages(event) {
        var connectionUrl = apiRoot;

        $.ajax({
            url: connectionUrl,
            method: "GET",
            dataType: 'json',
            success: getMessages,
            error: function () {
                console.log("Error fetching data from the server");
            }
        });
    }

    sortingBlock.change(function () {
        var selectedOption = $(this).val();
        var listItems = messagesList.children("li").get();

        listItems.sort(function (a, b) {
            var aTitle = $(a).find(".messageTitleBlock").text();
            var bTitle = $(b).find(".messageTitleBlock").text();
            var aDate = $(a).find(".messageDateBlock").text();
            var bDate = $(b).find(".messageDateBlock").text();

            switch (selectedOption) {
                case 'sortDateUp':
                    return aDate.localeCompare(bDate);
                    break;
                case 'sortDateDown':
                    return bDate.localeCompare(aDate);
                    break;
                case 'sortTitleUp':
                    return aTitle.localeCompare(bTitle);
                    break;
                case 'sortTitleDown':
                    return bTitle.localeCompare(aTitle);
                    break;
            }
        });

        $.each(listItems, function (i, li) {
            messagesList.append(li);
        });
    });

    getButton.on("click", getAllMessages);

    filterButton.on("click", function () {
        var pickedDate = datePicker.val();
        var connectionURL = apiRoot + "by-date/";

        if (pickedDate !== "") {
            var requestData = {
                timestamp: pickedDate + "T00:00:00"
            }

            $.ajax({
                url: connectionURL,
                method: "GET",
                contentType: "application/json",
                data: requestData,
                success: getMessages,
                error: function (xhr, textStatus, errorThrown) {
                    console.log("Error");
                }
            })
        }
    });

    filterButton.on("click", function () {
        var pickedDate = datePicker.val();

        if (pickedDate !== "") {
            // Format the pickedDate in the required format
            var formattedDate = pickedDate + "T00:00:00";
            var connectionURL = apiRoot + "by-date/";

            // Construct the query parameter string
            var queryString = "timestamp=" + formattedDate;
            // Append it to the URL
            connectionURL += "?" + queryString;

            $.ajax({
                url: connectionURL,
                method: "GET",
                contentType: "application/json", // Set the content type
                success: function (response) {
                    console.log("done");
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.log("Error");
                }
            });
        }
    });
});
