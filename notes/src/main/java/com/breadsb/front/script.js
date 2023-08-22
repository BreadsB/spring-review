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
            url: apiRoot,
            processData: false,
            method: 'POST',
            contentType: "application/json; charset=utf-8"
            dataType: 'json',
            data: JSON.stringfy({
                title: inputTitleValue,
                text: inputTextValue
            }),
            success: function(response) {
                if(data.status === 200) {
                    alert('Dane zostały przesłane na serwer.');
                }
            },
            error: function(xhr, status, error) {
                console.error('Wystąpił błąd podczas przesyłania danych: ' + error);
            }
        });
    }

    sendButton.on('click', function() {
        sendData();
    });

    clearButton.on('click', function() {
        inputText.val('');
    });

    function retrieveMessage() {
        $.ajax({
            url: apiRoot,
            method: GET,

        })
    }
});