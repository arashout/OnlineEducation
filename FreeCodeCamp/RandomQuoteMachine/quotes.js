//Global quotes list 
var quotesList = [];
var currentQuote;
var currentAuthor;
// A $( document ).ready() block.
$( document ).ready(function () {
    compileQuotes();
    //Initial Quote on Page Load
    $("#newQuoteButton").click(function(){
        updateQuote();
    });
    $('#tweetQuote').on('click', function() {
    openURL('https://twitter.com/intent/tweet?hashtags=quotes&related=freecodecamp&text=' + encodeURIComponent('"' + currentQuote + '" ' + currentAuthor));
});
});
function compileQuotes(){
    //A document I found which contains a bunch of quotes
    var url = "https://gist.githubusercontent.com/signed0/d70780518341e1396e11/raw/2a7f4af8d181a714f9d49105ed57fafb3f450960/quotes.json";
    
    $.ajax({ 
        url: url, success: function(data) {
        var stringData = data.replace(/[\[\]]/g,'');
        //Defining a quote object here
        function quoteObject(quote, author) {
          this.quote = quote;
          this.author = author;
        }      
        //I have to parse the JSON here, THIS MAY BE THE DUMBEST WAY TO DO IT
        //But I couldn't for the life of me couldn't figure out how to make 
        //JSON.parse work properly
        var stringList = stringData.split('\n');
        //The last entry is empty in THIS document 
        for(var i = 0; i < stringList.length - 1; i++){
            quoteWithAuthor = stringList[i].split('", "');
            //Replace the quotation marks in the document 
            for(var j = 0; j < quoteWithAuthor.length; j++){
                quoteWithAuthor[j] = quoteWithAuthor[j].replace(/[""]+/g, '')
            }
            aQuote = quoteWithAuthor[0];
            anAuthor = quoteWithAuthor[1];
            //Cleaned up strings can finally be added to objects
            quotesList.push(new quoteObject(aQuote, anAuthor));
        }
    }});
}
function getQuote(){
    var randIndex = Math.floor(Math.random() * quotesList.length);
    return quotesList[randIndex];
}

function getRandomDarkColor() {
    var letters = '012345'.split('');
    var color = '#';        
    color += letters[Math.round(Math.random() * 5)];
    letters = '0123456789ABCDEF'.split('');
    for (var i = 0; i < 5; i++) {
        color += letters[Math.round(Math.random() * 15)];
    }
    return color;
}  
function get_random_color() {
    var letters = 'ABCDE'.split('');
    var color = '#';
    for (var i=0; i<3; i++ ) {
        color += letters[Math.floor(Math.random() * letters.length)];
    }
    return color;
}
function changeTextColor(color){
    $("#quoteBox").animate({color: color}, 1000);
}
function changeBackground(color) {
    $("body").animate({backgroundColor: color}, 1000);
}
function updateQuote(){
    var quoteObj = getQuote();
    currentQuote = quoteObj.quote;
    currentAuthor = quoteObj.author;
    $("#quoteText").text(currentQuote);
    $("#quoteAuthor").text(currentAuthor);
    var randColour = getRandomDarkColor();
    changeTextColor(randColour);
    changeBackground(randColour);
}
function openURL(url){
  window.open(url, 'Share', 'width=550, height=400, toolbar=0, scrollbars=1 ,location=0 ,statusbar=0,menubar=0, resizable=0');
}
