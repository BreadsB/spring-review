$(document).ready(function () {
    var noteForm = $('[data-note-add-form]')
    var sendButton = $('#sendButton');
    var clearButton = $('#clearButton');
    var apiRoot = "http://localhost:8080/api/notes/";
    var inputTitle = $('#inputTitle');
    var inputText = $('#inputText');

    function postData(event) {
        event.preventDefault();

        var inputTitleValue = inputTitle.val();
        var inputTextValue = inputText.val();
        var requestUrl = apiRoot;
        
        $.ajax({
            url: requestUrl,
            method: 'POST',
            processData: false,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                title: inputTitleValue,
                body: inputTextValue
            }),
            success: function (response) {
                console.log(response.status);
                if (response.status === 200) {
                    console.log('Dane zostały przesłane na serwer.');
                }
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log('Error occured' + textStatus);
            }
        });
        
        cleanData(inputTitle);
        cleanData(inputText);
    }

    noteForm.on('submit', postData);
    
    function cleanData(element) {
        element.val('');
    }
    
});
