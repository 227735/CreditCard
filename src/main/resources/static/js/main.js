async function logProducts() {
    const response = await fetch("/api/products")
    const response = await response.json();
    console.log(products);
}

alert("it works");