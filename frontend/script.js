$(document).ready(function() {
    const inputText = $('#inputText');
    const sendButton = $('#sendButton');
    const clearButton = $('#clearButton');
    const myForm = $('#myForm');

    sendButton.on('click', function() {
        const inputValue = inputText.val();
        $.ajax({
            url: 'teachers', // Zmień na właściwą ścieżkę do swojego serwera
            method: 'POST',
            data: { text: inputValue },
            success: function(response) {
                alert('Dane zostały przesłane na serwer.');
            },
            error: function(xhr, status, error) {
                console.error('Wystąpił błąd podczas przesyłania danych: ' + error);
            }
        });
    });

    clearButton.on('click', function() {
        inputText.val('');
    });
});