document.body.addEventListener('htmx:beforeSwap', (event) => {
    if (event.target.classList.contains('ai-response')) {
        event.preventDefault();

        const responseHTML = event.detail.xhr.responseText;
        chatgpt(event.target, responseHTML);
    }
});

function activate(element) {
    document.querySelectorAll('.nav-link').forEach(el => {
        if (el !== element) el.classList.remove('active');
    });
    element.classList.add('active');
}

function chatgpt(element, text) {

    const fullText = text;
    const streamContainer = element;

    const measureEl = document.createElement("div");
    const computed = getComputedStyle(streamContainer);

    measureEl.style.minWidth = streamContainer.clientWidth + "px";
    measureEl.style.maxWidth = streamContainer.clientWidth + "px";
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
            streamContainer.innerHTML = fullText.slice(0, index + 2) + `<span class="result-streaming">⬤</span>`;
            const char = fullText[index];
            index += 2;
            const delay = char === ' ' ? 70 : 10;
            setTimeout(processCharacter, delay);
        } else {
            streamContainer.innerHTML = fullText;
        }
    }

    processCharacter();
}

