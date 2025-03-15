import { useState } from "react";
import "./ImageUploader.css";

const ImageUploader = ({ onImageUpload }) => {
  const [imagePreview, setImagePreview] = useState(null);
  const [error, setError] = useState("");

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      // Validar formato
      const validFormats = ["image/jpeg", "image/png", "image/gif"];
      if (!validFormats.includes(file.type)) {
        setError("Formato no válido. Solo JPG, PNG y GIF.");
        return;
      }

      // Crear vista previa
      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreview(reader.result);
        setError("");
        if (onImageUpload) {
          onImageUpload(file);
        }
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="image-uploader">
      <label>Subir Imagen:</label>
      <input type="file" accept="image/*" onChange={handleImageChange} />
      {error && <span className="error">{error}</span>}
      {imagePreview && (
        <img src={imagePreview} alt="Vista previa" className="preview" />
      )}
    </div>
  );
};

export default ImageUploader;
