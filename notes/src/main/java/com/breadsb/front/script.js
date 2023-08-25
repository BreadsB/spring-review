$(document).ready(function () {
    var inputTitle = $('#inputTitle');
    var inputText = $('#inputText');
    var sendButton = $('#sendButton');
    var clearButton = $('#clearButton');
    var apiRoot = "http://localhost:8080/notes/api/notes/";

    function postData(event) {
        event.preventDefault();
        
        var inputTitleValue = $(this).find('[id="inputTitle"]').val();
        var inputTextValue = $(this).find('[id="inputText"]').val();
        var requestUrl = apiRoot;
        
        $.ajax({
            url: requestUrl,
            processData: false,
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                title: inputTitleValue,
                text: inputTextValue
            }),
            success: function (response) {
                if (data.status === 200) {
                    alert('Dane zostały przesłane na serwer.');
                }
            },
            error: function (xhr, status, error) {
                console.error('Wystąpił błąd podczas przesyłania danych: ' + error);
            }
        });
    }

    sendButton.on('click', function () {
        sendData();
    });

    clearButton.on('click', function () {
        inputText.val('');
    });
});
