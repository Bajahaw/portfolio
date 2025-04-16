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
    streamContainer.innerHTML = '';

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
    // measureEl.style.border = "1px solid red"; // for debug purposes
    measureEl.textContent = fullText;

    document.body.appendChild(measureEl);
    const finalHeight = measureEl.offsetHeight;
    measureEl.remove();

    requestAnimationFrame(() => {
        streamContainer.style.height = finalHeight + 'px';
    });

    let index = 0;

    function processCharacter() {
        if (index < fullText.length) {
            const sentenceBlock = document.createElement('span');
            sentenceBlock.className = 'ease-long opacity-0';
            sentenceBlock.textContent = fullText.slice(index, index + 2);
            streamContainer.append(sentenceBlock);

            const char = fullText[index+2];
            const delay = char === '.' ? 100 : 10;
            index += 2;

            setTimeout(() => sentenceBlock.classList.add('opacity-100'), 100);
            setTimeout(processCharacter, delay);

        } else {
            setTimeout(() => {
                streamContainer.innerHTML = fullText;
                streamContainer.style.height = 'revert';
            }, 2000);
        }
    }

    processCharacter();
}

