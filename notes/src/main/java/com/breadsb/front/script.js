$(document).ready(function() {
    const inputTitle = $('#inputTitle');
    const inputText = $('#inputText');
    const sendButton = $('#sendButton');
    const clearButton = $('#clearButton');
    const apiRoot = "http://localhost:8080/v1/tasks";

    function sendData() {
        const inputTitleValue = inputTitle.val();
        const inputTextValue = inputText.val();
        $.ajax({
            url: '/api/',
            method: 'POST',
            data: {
                title: inputTitleValue,
                text: inputTextValue
            },
            success: function(response) {
                alert('Dane zostały przesłane na serwer.');
            },
            error: function(xhr, status, error) {
                console.error('Wystąpił błąd podczas przesyłania danych: ' + error);
            }
        });
    }


    sendButton.on('click', function() {
    });

    clearButton.on('click', function() {
        inputText.val('');
    });
});