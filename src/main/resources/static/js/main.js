document.body.addEventListener('htmx:beforeSwap', (event) => {
    if (event.target.classList.contains('ai-response')) {
        event.preventDefault();

        const responseHTML = event.detail.xhr.responseText;
        chatgpt(event.target, responseHTML);
    }
});



function chatgpt(element, text) {

    const fullText = text;
    const streamContainer = element;

    const measureEl = document.createElement("div");
    const computed = getComputedStyle(streamContainer);
    
    measureEl.style.width = streamContainer.clientWidth + "px";
    measureEl.style.padding = computed.padding;
    measureEl.style.font = computed.font;
    measureEl.style.lineHeight = computed.lineHeight;
    measureEl.style.boxSizing = computed.boxSizing;
    measureEl.style.position = "absolute";
    measureEl.style.visibility = "hidden";
    measureEl.style.border = "1px solid red";
    measureEl.textContent = fullText;
    
    document.body.appendChild(measureEl);
    const finalHeight = measureEl.offsetHeight;
    console.log(finalHeight);
    measureEl.remove();

    requestAnimationFrame(() => {
        streamContainer.style.height = finalHeight + 'px';
    });

    let index = 0;
    function processCharacter() {
        if (index < fullText.length) {
            streamContainer.innerHTML = fullText.slice(0, index + 2) + `<span class="result-streaming"></span>`;
            const char = fullText[index];
            index += 2;
            const delay = char === ' ' ? 70 : 10;
            setTimeout(processCharacter, delay);
        }
    }

    processCharacter();

    // var typewriter = new Typewriter(element, {
    //     loop: false,
    //     delay: 10,
    //     cursor: ''
    // });

    // typewriter
    //     .pauseFor(500)
    //     .typeString('<span class="text-primary text-opacity-75">AI: </span> ');

    // text.match(/[^ ]+ ?/g).forEach(word => {
    //     typewriter
    //         .typeString(word)
    //         .pauseFor(45);
    // });

    // typewriter
    //     .pauseFor(500)
    //     .callFunction(() => element.classList.remove('result-streaming'))
    //     .start()
}

