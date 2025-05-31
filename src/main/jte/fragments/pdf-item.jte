<div class="modal fade vh-100 vw-100"
     id="pdfModal"
     tabindex="-1"
     aria-hidden="true">
    <div class="modal-dialog modal-xl my-4">
        <div class="modal-content">
            <div class="modal-header justify-content-center">
                <h5 class="modal-title">Resume</h5>
            </div>
            <div class="modal-body position-relative py-4" id="pdfModalBody">
                <div id="loadingSpinner"
                     class="position-absolute top-50 start-50 translate-middle">
                    <div class="spinner-border"></div>
                </div>
                <div class="text-center">
                    <canvas id="resume"></canvas>
                </div>
            </div>
            <div class="modal-footer">
                <button hx-get="/content/download-cv" class="btn btn-dark rounded-pill m-1">
                    <i class="bi bi-download me-2"></i>Download
                </button>
                <button class="btn btn-outline-dark rounded-pill"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<script defer src="https://cdn.jsdelivr.net/npm/pdfjs-dist@2.16.105/build/pdf.min.js"></script>