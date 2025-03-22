import "./FormInput.css";

const FormInput = ({ label, type, name, value, onChange, errors }) => {
  return (
    <div className="form-group">
      <label>{label}:</label>
      {type === "textarea" ? (
        <textarea
          name={name}
          value={value}
          onChange={onChange}
          rows={5} // ✅ Especifica el número de renglones
        />
      ) : (
        <input type={type} name={name} value={value} onChange={onChange} />
      )}
      {errors && <span className="error">{errors}</span>}
    </div>
  );
};

export default FormInput;
