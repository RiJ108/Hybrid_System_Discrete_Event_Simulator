

public class Buffer extends Component{
	private int q = 0;
	
	public Buffer() {
		init();

	}
	public Buffer(String name) {
		init();
		setName(name);
	}
	
	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}

	@Override
	public boolean external() {
		// TODO Auto-generated method stub
		boolean tmp;
		int current_state = getCurrent_state();
		if(current_state == 0 && getInputs().get(0).getValue() != null) {
			q++;
			isSwitched(true);
			setNext_state(1);
		}else if(current_state == 1 && getInputs().get(0).getValue() != null) {
			q++;
			isSwitched(true);
			setNext_state(1);
		}else if(current_state == 2 && getInputs().get(0).getValue() != null) {
			q++;
			isSwitched(true);
			setNext_state(2);
		}else if(current_state == 2 && getInputs().get(1).getValue() != null) {
			if(q > 0) {
				isSwitched(true);
				setNext_state(1);
			}else {
				isSwitched(true);
				setNext_state(0);
			}
		}
		tmp = isSwitched();
		swapState();
		return tmp;
	}

	@Override
	public void internal() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			q--;
			isSwitched(true);
			setNext_state(2);
		}
		swapState();
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			getOutputs().get(0).setValue(true, true);
			break;
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return Double.POSITIVE_INFINITY;
		case 1:
			return 0.0;
		case 2:
			return Double.POSITIVE_INFINITY;
		}
		return 0;
	}
}
