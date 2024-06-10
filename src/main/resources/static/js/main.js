// API CONTEXT
getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

getCurrentOffer = () => {
    return fetch("/api/current-offer")
         .then(response => response.json());
    }

const addProductToCArt = (productId) => {
return fetch(`/api/add-to-cart/${productId}`{
    method: 'POST'
    });
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    })
        .then(response => response.json());
    }


const createProductHtml = (productData) => {
    const template = `
        <div>
            <h4>${productData.name}</h4>
            <span>${productData.price}</span>
            <img src = "https://images.prismic.io/carwow/e2dbfc3f-127b-4de0-a839-e082dc488eb4_RHD+BMW+M8+Exterior+2.jpg"/>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    `
    const productEl = document.createElement("li");
    productEl.innerHTML = template.trim();

    return productEl;
}

const refreshCurrentOffer = () => {
    const totalEl = document.querySelector('#offer__total');
    const itemCountEl = document.querySelector('#offer__items-count');

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemCountEl.textContent = `${offer.itemsCount}`;
        })
}

const initializeCartHandler = (productHtmlEl) => {
    const addToCArtBtn = productHtmlEl.querySelector("button[data-id]");
    addToCArtBtn.addEventListener("click", (event) => {
        const productId = event.target.getAttribute("data-id");
        addProductToCArt(productId)
            .then(refreshCurrentOffer());
    });
    return productHtmlEl;
}

const checkoutFormEl = document.querySelector('#checkout');
checkoutFormEl.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstName: checkoutFormEl.querySelector('input[name="first_name"]'), value,
        lastName: checkoutFormEl.querySelector('input[name="last_name"]'), value,
        email: checkoutFormEl.querySelector('input[name="email"]'), value,
    }

    acceptOffer(acceptOfferRequest)
        then(resDetails => {window.location.href = resDetails.paymentUrl})
});

document.addEventListener("DOMContentLoaded", () => {
console.log("it works");
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJson => productsAsJson.map(createProductHtmlEl))
        .then(productsHtmls => productsHtmls.map(initializeCartHandler))
        .then(productsHtmls => {
            productsHtmls.forEach(htmlEl => productsList.appendChild(htmlEl))
        });
});