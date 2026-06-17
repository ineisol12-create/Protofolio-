// Target all elements containing the class 'draggable'
const draggables = document.querySelectorAll('.draggable');

draggables.forEach(item => {
    let isDragging = false;
    let offsetX, offsetY;

    item.addEventListener('mousedown', (e) => {
        isDragging = true;
        // Calculate the mouse position relative to the element's corner
        offsetX = e.clientX - item.getBoundingClientRect().left;
        offsetY = e.clientY - item.getBoundingClientRect().top;
        item.style.transition = 'none'; // Disable smooth transition while dragging
    });

    document.addEventListener('mousemove', (e) => {
        if (!isDragging) return;
        
        // Find the canvas container boundaries
        const canvas = document.querySelector('.scrapbook-canvas').getBoundingClientRect();
        
        // Calculate position relative to the canvas container
        let x = e.clientX - canvas.left - offsetX;
        let y = e.clientY - canvas.top - offsetY;

        // Apply new positions
        item.style.left = `${x}px`;
        item.style.top = `${y}px`;
    });

    document.addEventListener('mouseup', () => {
        if (isDragging) {
            isDragging = false;
            item.style.transition = 'transform 0.15s ease, box-shadow 0.15s ease';
        }
    });

    // Mobile Touchscreen Support
    item.addEventListener('touchstart', (e) => {
        isDragging = true;
        const touch = e.touches[0];
        offsetX = touch.clientX - item.getBoundingClientRect().left;
        offsetY = touch.clientY - item.getBoundingClientRect().top;
        item.style.transition = 'none';
    });

    document.addEventListener('touchmove', (e) => {
        if (!isDragging) return;
        const canvas = document.querySelector('.scrapbook-canvas').getBoundingClientRect();
        const touch = e.touches[0];
        let x = touch.clientX - canvas.left - offsetX;
        let y = touch.clientY - canvas.top - offsetY;

        item.style.left = `${x}px`;
        item.style.top = `${y}px`;
    });

    document.addEventListener('touchend', () => {
        isDragging = false;
    });
});
      
