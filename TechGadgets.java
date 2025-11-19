

// Form Validation for Enquiry Page
function validateEnquiryForm(event) {
  event.preventDefault();
  
  let isValid = true;
  let errorMessages = [];
  
  // Get form fields
  const fullName = document.getElementById('full-name').value.trim();
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const product = document.getElementById('product').value.trim();
  const quantity = document.getElementById('quantity').value;
  
  // Clear previous error messages
  clearErrors();
  
  // Validate Full Name
  if (fullName === '' || fullName.length < 3) {
    isValid = false;
    errorMessages.push('Please enter your full name (at least 3 characters)');
    showError('full-name', 'Name must be at least 3 characters');
  }
  
  // Validate Email
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email)) {
    isValid = false;
    errorMessages.push('Please enter a valid email address');
    showError('email', 'Invalid email format');
  }
  
  // Validate Phone (South African format)
  const phonePattern = /^(\+27|0)[0-9]{9}$/;
  if (!phonePattern.test(phone.replace(/\s/g, ''))) {
    isValid = false;
    errorMessages.push('Please enter a valid South African phone number (e.g., +27123456789 or 0123456789)');
    showError('phone', 'Invalid phone number format');
  }
  
  // Validate Product
  if (product === '') {
    isValid = false;
    errorMessages.push('Please specify what product you are looking for');
    showError('product', 'This field is required');
  }
  
  // Validate Quantity
  if (quantity === '') {
    isValid = false;
    errorMessages.push('Please select a quantity');
    showError('quantity', 'Please select a quantity');
  }
  
  // If form is valid, show success message
  if (isValid) {
    showSuccessMessage('enquiry');
    // In real implementation, you would submit the form here via AJAX
    return true;
  } else {
    showErrorSummary(errorMessages);
    return false;
  }
}

// Form Validation for Contact Page
function validateContactForm(event) {
  event.preventDefault();
  
  let isValid = true;
  let errorMessages = [];
  
  // Get form fields
  const fullName = document.getElementById('full-name').value.trim();
  const email = document.getElementById('email').value.trim();
  const phone = document.getElementById('phone').value.trim();
  const subject = document.getElementById('subject').value.trim();
  const message = document.getElementById('message').value.trim();
  
  // Clear previous error messages
  clearErrors();
  
  // Validate Full Name
  if (fullName === '' || fullName.length < 3) {
    isValid = false;
    errorMessages.push('Please enter your full name (at least 3 characters)');
    showError('full-name', 'Name must be at least 3 characters');
  }
  
  // Validate Email
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email)) {
    isValid = false;
    errorMessages.push('Please enter a valid email address');
    showError('email', 'Invalid email format');
  }
  
  // Validate Phone (South African format)
  const phonePattern = /^(\+27|0)[0-9]{9}$/;
  if (!phonePattern.test(phone.replace(/\s/g, ''))) {
    isValid = false;
    errorMessages.push('Please enter a valid South African phone number');
    showError('phone', 'Invalid phone number format');
  }
  
  // Validate Subject
  if (subject === '' || subject.length < 5) {
    isValid = false;
    errorMessages.push('Please enter a subject (at least 5 characters)');
    showError('subject', 'Subject must be at least 5 characters');
  }
  
  // Validate Message
  if (message === '' || message.length < 10) {
    isValid = false;
    errorMessages.push('Please enter a message (at least 10 characters)');
    showError('message', 'Message must be at least 10 characters');
  }
  
  // If form is valid, show success message
  if (isValid) {
    showSuccessMessage('contact');
    return true;
  } else {
    showErrorSummary(errorMessages);
    return false;
  }
}

// Helper function to show error next to field
function showError(fieldId, message) {
  const field = document.getElementById(fieldId);
  field.style.borderColor = '#ef4444';
  
  // Create error message element
  const errorDiv = document.createElement('div');
  errorDiv.className = 'error-message';
  errorDiv.style.color = '#ef4444';
  errorDiv.style.fontSize = '0.875rem';
  errorDiv.style.marginTop = '0.25rem';
  errorDiv.textContent = message;
  
  // Insert error message after the field
  field.parentNode.insertBefore(errorDiv, field.nextSibling);
}

// Helper function to clear all errors
function clearErrors() {
  // Remove all error messages
  const errorMessages = document.querySelectorAll('.error-message');
  errorMessages.forEach(msg => msg.remove());
  
  // Reset border colors
  const inputs = document.querySelectorAll('input, select, textarea');
  inputs.forEach(input => {
    input.style.borderColor = '';
  });
  
  // Remove error summary if exists
  const errorSummary = document.getElementById('error-summary');
  if (errorSummary) {
    errorSummary.remove();
  }
}

// Show error summary at top of form
function showErrorSummary(messages) {
  const form = document.querySelector('form');
  const summaryDiv = document.createElement('div');
  summaryDiv.id = 'error-summary';
  summaryDiv.style.backgroundColor = '#fee2e2';
  summaryDiv.style.border = '2px solid #ef4444';
  summaryDiv.style.borderRadius = '0.5rem';
  summaryDiv.style.padding = '1rem';
  summaryDiv.style.marginBottom = '1rem';
  
  let summaryHTML = '<h3 style="color: #ef4444; margin-bottom: 0.5rem;">Please fix the following errors:</h3><ul style="margin-left: 1.5rem;">';
  messages.forEach(msg => {
    summaryHTML += `<li style="color: #dc2626;">${msg}</li>`;
  });
  summaryHTML += '</ul>';
  
  summaryDiv.innerHTML = summaryHTML;
  form.insertBefore(summaryDiv, form.firstChild);
  
  // Scroll to error summary
  summaryDiv.scrollIntoView({ behavior: 'smooth', block: 'center' });
}

// Show success message
function showSuccessMessage(formType) {
  const form = document.querySelector('form');
  const successDiv = document.createElement('div');
  successDiv.style.backgroundColor = '#d1fae5';
  successDiv.style.border = '2px solid #10b981';
  successDiv.style.borderRadius = '0.5rem';
  successDiv.style.padding = '1.5rem';
  successDiv.style.marginBottom = '1rem';
  successDiv.style.textAlign = 'center';
  
  if (formType === 'enquiry') {
    successDiv.innerHTML = '<h3 style="color: #10b981; margin-bottom: 0.5rem;">✓ Enquiry Submitted Successfully!</h3><p style="color: #059669;">Thank you for your enquiry. We will respond within 2 hours with a personalized quote.</p>';
  } else {
    successDiv.innerHTML = '<h3 style="color: #10b981; margin-bottom: 0.5rem;">✓ Message Sent Successfully!</h3><p style="color: #059669;">Thank you for contacting us. We will respond within 24 hours.</p>';
  }
  
  form.insertBefore(successDiv, form.firstChild);
  form.reset();
  
  // Scroll to success message
  successDiv.scrollIntoView({ behavior: 'smooth', block: 'center' });
}

// Smooth Scroll for Navigation Links
function smoothScroll() {
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();
      const target = document.querySelector(this.getAttribute('href'));
      if (target) {
        target.scrollIntoView({
          behavior: 'smooth',
          block: 'start'
        });
      }
    });
  });
}

// Product Card Hover Animation Enhancement
function enhanceProductCards() {
  const productCards = document.querySelectorAll('.product-card');
  productCards.forEach(card => {
    card.addEventListener('mouseenter', function() {
      this.style.transform = 'translateY(-10px) scale(1.02)';
    });
    card.addEventListener('mouseleave', function() {
      this.style.transform = 'translateY(0) scale(1)';
    });
  });
}

// Initialize all JavaScript when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
  // Check if we're on enquiry page
  const enquiryForm = document.querySelector('.enquiry-section form');
  if (enquiryForm) {
    enquiryForm.addEventListener('submit', validateEnquiryForm);
  }
  
  // Check if we're on contact page
  const contactForm = document.querySelector('.contact-form form');
  if (contactForm) {
    contactForm.addEventListener('submit', validateContactForm);
  }
  
  // Initialize smooth scroll
  smoothScroll();
  
  // Enhance product cards
  enhanceProductCards();
  
  // Add animation to elements on scroll
  addScrollAnimations();
});

// Add scroll animations
function addScrollAnimations() {
  const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
  };
  
  const observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.style.opacity = '1';
        entry.target.style.transform = 'translateY(0)';
      }
    });
  }, observerOptions);
  
  // Observe all sections
  document.querySelectorAll('section').forEach(section => {
    section.style.opacity = '0';
    section.style.transform = 'translateY(20px)';
    section.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
    observer.observe(section);
  });
}