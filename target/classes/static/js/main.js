getProducts() => {
   return fetch("/api/products")
    .then(response => response.json())

}
    const createProductHtml = () => {
    const template = `
        <div>
            <h4>${productData.name}</h4>
            <span>${productData.price}</span>
            <img src="h"ttps://picsum.photos/200/300?grayscale"/>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    `;
        const productEl = document.createElement('li');
        productEl.innerHTML = "abc xyz";
        return productEL;
    }

document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
    .then(productsAsJsonObj => productsAsJsonObj.map(product => createProductHtml))
    .then(productsAsHtmlEl => {
        forEach(productEl => productListEl.appendChild(productEl)))
    }

});