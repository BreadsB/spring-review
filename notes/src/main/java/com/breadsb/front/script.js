$(document).ready(function () {
    var noteForm = $('[data-note-add-form]')
    var sendButton = $('#sendButton');
    var clearButton = $('#clearButton');
    var apiRoot = "http://localhost:8080/api/notes/";

    function postData(event) {
        event.preventDefault();

        var inputTitleValue = $('#inputTitle').val();
        var inputTextValue = $('#inputText').val();
        var requestUrl = apiRoot;

        console.log(inputTitleValue);
        console.log(inputTextValue);

        $.ajax({
            url: requestUrl,
            method: 'POST',
            processData: false,
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

    noteForm.on('submit', postData);

});
