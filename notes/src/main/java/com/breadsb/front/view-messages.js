$(document).ready(function() {
    var messages_list = $('[messages_list]');
    var retrieveButton = $('#retrieveButton');
    var apiRoot = 'http://localhost:8080/api/notes/';

    function getAllMessages() {
        var requestUrl = apiRoot;

        $.ajax({
            url: requestUrl,
            method: 'GET',
            success: function(data) {
                fillListWithMessages(data);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    };

    function fillListWithMessages(data) {
        messages_list.empty();
        $.each(data.messages, function(index, message) {
            messages_list.append($('<option value="' +
            message.title + '">' + message.title + '</option>'));
        });
    };

    function changeName() {
        retrieveButton.text("Changed name!");
    }

    retrieveButton.click(getAllMessages);
});