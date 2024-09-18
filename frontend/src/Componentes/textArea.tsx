interface TextAreaProps {
    label: string;
  }
  
  export function TextArea({ label }: TextAreaProps) {
    return (
      <div>
        <label>{label}</label>
        <textarea rows={4} cols={50} />
      </div>
    );
  }