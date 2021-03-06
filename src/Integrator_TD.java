
public class Integrator_TD extends Component{
	private double value;
	private double step;
	private double delta;
	private double deri;
	
	public Integrator_TD() {
		init();
		setName("Integrator");
		setStep(0.1);
		setCI(0.0);
		setDeri(0.0);
	}
	
	public Integrator_TD(String name) {
		init();
		setName(name);
		setStep(0.1);
		setCI(0.0);
		setDeri(0.0);
	}
	
	public Integrator_TD(String name, double step) {
		init();
		setName(name);
		setStep(step);
		setCI(0.0);
		setDeri(0.0);
	}
	
	public Integrator_TD(double step) {
		init();
		setName("Integrator");
		setStep(step);
		setCI(0.0);
		setDeri(0.0);
	}
	
	public double getDeri() {
		return deri;
	}

	public void setDeri(double deri) {
		this.deri = deri;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}
	
	public void integrate() {
		setValue((double)getValue() + getDelta()*getDeri());
	}

	@Override
	public boolean external() {
		// TODO Auto-generated method stub
		setDeri((double)getInputs().get(0).getValue());
		switch(getCurrent_state()) {
		case 0:
			setDelta(getE());
			isSwitched(true);
			setNext_state(1);
		}
		return swapState();
	}

	@Override
	public boolean internal() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			setDelta(getE());
			isSwitched(true);
			setNext_state(1);
			break;
		case 1:
			integrate();
			isSwitched(true);
			setNext_state(0);
			break;
		}
		return swapState();
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			if(getOutputs().size() != 0) {
				getOutputs().get(0).setValue(value);
			}
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return step;
		case 1:
			return 0.0;
		}
		return 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		this.value = (double)value;
	}
	
	public void setCI(Object value) {
		setValue(value);
	}

}
