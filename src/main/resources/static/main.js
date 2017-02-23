/**
 * Created by Artis on 2/21/2017.
 */

$(document).ready(function () {

    var message = $("#message");
    var submitButton = $("#submit");
    var phoneNumberField = $("#phoneNumber");

    $.validate({
        validateOnBlur: false,
        onSuccess: function (e) {
            submitButton.find("i").removeClass("hidden");
            var data = {phoneNumber: phoneNumberField.val().replace(/\s/g, "")};
            $.ajax({
                url: "/country/phone",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                complete: function (xhr) {
                    onResult(xhr);
                }
            });

            return false;
        }
    });

    function onResult(xhr) {
        submitButton.find("i").addClass("hidden");
        var messageText = "";
        var responseJSON = xhr.responseJSON;

        if (xhr.status >= 500) {
            messageText = "Service is not available";
        } else if (xhr.status >= 400) {
            messageText = responseJSON.message;
        } else {
            messageText = responseJSON.name;
        }
        message.text(messageText);
    }

});
