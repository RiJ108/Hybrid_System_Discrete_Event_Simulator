import java.util.ArrayList;

import chart.Chart;
import chart.ChartFrame;

public class Scheduler {
	private static double t;
	private static double tf;
	private static double tr_min;
	private static ArrayList<Component> components;
	private static ArrayList<Component> imms;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//exo_1();
		//exo_2();
		//exo_2b();
		//exo_3();
		exo_4();
		System.out.println("**********************************\nDone.");
	}
	
	public static void exo_4() {
		components = new ArrayList<Component>();
		tf = 40.0;
		init_exo_4();
		run_exo_4();
	}
	
	public static void init_exo_4() {
		double step = 0.01;
		
		Output x_o0 = new Output("Bouncer_x(out)");
		Input x_i0 = new Input("Integrator_0_x(in)", x_o0);
		
		Output px_o0 = new Output("Integrator_0_px(out)");
		Input px_i0 = new Input("Integrator_1_px(in)", px_o0);
		
		Output p2x_o0 = new Output("Integrator_1_ppx(out)");
		Input p2x_i0 = new Input("Bouncer_ppx(in)", p2x_o0);
		
		Integrator_ED integrator_0 = new Integrator_ED("Integrator_0", step);
		integrator_0.addInput(x_i0);
		integrator_0.addOutput(px_o0);
		
		Integrator_ED integrator_1 = new Integrator_ED("Integrator_1", step);
		integrator_1.setdQ(step*0.1);
		integrator_1.setValue(100.0);
		integrator_1.addInput(px_i0);
		integrator_1.addOutput(p2x_o0);
		
		Bouncer bouncer = new Bouncer(-9.81, 0.8);
		bouncer.addInput(p2x_i0);
		bouncer.addOutput(x_o0);
		bouncer.setIntegrator(integrator_0);
		
		components.add(bouncer);
		components.add(integrator_0);
		components.add(integrator_1);
	}
	
	public static void run_exo_4() {
		ChartFrame chartframe_4 = new ChartFrame("Resultat Exercie 4", "Bouncer > Integrator >\\< Integrator");
		Chart ground = new Chart("Ground");
		Chart x = new Chart("X(Accelaration)");
		Chart px = new Chart("pX(Speed)");
		Chart p2x = new Chart("ppX(Position)");
		chartframe_4.addToLineChartPane(ground);
		chartframe_4.addToLineChartPane(x);
		chartframe_4.addToLineChartPane(px);
		chartframe_4.addToLineChartPane(p2x);
		while(t <= tf) {
			ground.addDataToSeries(t, 0.0);
			x.addDataToSeries(t, (double)components.get(0).getValue());
			px.addDataToSeries(t, (double)components.get(1).getValue());
			p2x.addDataToSeries(t, (double)components.get(2).getValue());
			getTr_min();
			updateImms();
			stepTime();
			updateOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void exo_3() {
		components = new ArrayList<Component>();
		tf = 3.5;
		init_exo_3();
		run_exo_3(false);
	}
	
	public static void init_exo_3() {
		double step = 0.001;
		
		Output x_o0 = new Output("Constante_x(out)");
		Input x_i0 = new Input("Integrator_0_x(in)", x_o0);
		
		Output px_o0 = new Output("Integrator_0_px(out)");
		Input px_i0 = new Input("Integrator_1_px(in)", px_o0);
		
		Constante constante = new Constante(-9.81);
		constante.addOutput(x_o0);
		
		Integrator_ED integrator_0b = new Integrator_ED("Integrator_0", step);
		integrator_0b.addInput(x_i0);
		integrator_0b.addOutput(px_o0);
		
		Integrator_ED integrator_1b = new Integrator_ED("Integrator_1", step);
		integrator_1b.addInput(px_i0);
		
		components.add(constante);
		components.add(integrator_0b);
		components.add(integrator_1b);
	}
	
	public static void run_exo_3(boolean show) {
		ChartFrame chartframe_3 = new ChartFrame("Resultat Exercie 3", "Constante > Integrator > Integrator");
		Chart x = new Chart("X");
		Chart px = new Chart("pX");
		Chart p2x = new Chart("ppX");
		chartframe_3.addToLineChartPane(x);
		chartframe_3.addToLineChartPane(px);
		chartframe_3.addToLineChartPane(p2x);
		while(t <= tf) {
			x.addDataToSeries(t, (double)components.get(0).getValue());
			px.addDataToSeries(t, (double)components.get(1).getValue());
			p2x.addDataToSeries(t, (double)components.get(2).getValue());
			getTr_min();
			updateImms();
			stepTime();
			updateOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void exo_2b() {
		components = new ArrayList<Component>();
		tf = 2.5;
		init_exo_2b();
		run_exo_2b(false);
	}
	
	public static void init_exo_2b() {
		double step = 0.001;
		
		Output y_o0 = new Output("Stepper_0_y(out)");
		Input y_i0 = new Input("Adder_y0(in)", y_o0);
		
		Output y_o1 = new Output("Stepper_0_y(out)");
		Input y_i1 = new Input("Adder_y1(in)", y_o1);
		
		Output y_o2 = new Output("Stepper_0_y(out)");
		Input y_i2 = new Input("Adder_y2(in)", y_o2);
		
		Output y_o3 = new Output("Stepper_0_y(out)");
		Input y_i3 = new Input("Adder_y3(in)", y_o3);
		
		Output s_o0 = new Output("Adder_i0(out)");
		Input s_i0 = new Input("Integrator_0_s0(in)", s_o0);
		
		Output sp_o0 = new Output("Integrator_0_s0(out)");
		Input sp_i0 = new Input("Integrator_1_s0(in)", sp_o0);
		
		Stepper stepper_0 = new Stepper("Stepper_0", 1.0, -3.0, 0.65);
		stepper_0.addOutput(y_o0);
		
		Stepper stepper_1 = new Stepper("Stepper_1", 0.0, 1.0, 0.35);
		stepper_1.addOutput(y_o1);
		
		Stepper stepper_2 = new Stepper("Stepper_2", 0.0, 1.0, 1.0);
		stepper_2.addOutput(y_o2);
		
		Stepper stepper_3 = new Stepper("Stepper_3", 0.0, 4.0, 1.5);
		stepper_3.addOutput(y_o3);
		
		Adder adder = new Adder();
		adder.addInput(y_i0);
		adder.addInput(y_i1);
		adder.addInput(y_i2);
		adder.addInput(y_i3);
		adder.addOutput(s_o0);
		
		Integrator_ED integrator_0b = new Integrator_ED("Integrator_0", step);
		integrator_0b.addInput(s_i0);
		integrator_0b.addOutput(sp_o0);
		
		Integrator_ED integrator_1b = new Integrator_ED("Integrator_1", step);
		integrator_1b.addInput(sp_i0);
		
		components.add(stepper_0);
		components.add(stepper_1);
		components.add(stepper_2);
		components.add(stepper_3);
		components.add(adder);
		components.add(integrator_0b);
		components.add(integrator_1b);
	}
	
	public static void run_exo_2b(boolean show) {
		ChartFrame chartframe_2b = new ChartFrame("Resultat Exercice 2b(evenement discret)", "Stepper > Adder > Integrator > Integrator");
		Chart cDeri = new Chart("Derivate");
		Chart cInte = new Chart("Integrale");
		Chart cInte2 = new Chart("Double_Integrale");
		chartframe_2b.addToLineChartPane(cDeri);
		chartframe_2b.addToLineChartPane(cInte);
		chartframe_2b.addToLineChartPane(cInte2);
		while(t <= tf) {
			cDeri.addDataToSeries(t, (double)components.get(4).getValue());
			cInte.addDataToSeries(t, (double)components.get(5).getValue());
			cInte2.addDataToSeries(t, (double)components.get(6).getValue());
			getTr_min();
			updateImms();
			stepTime();
			updateOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void exo_2() {
		components = new ArrayList<Component>();
		tf = 2.5;
		init_exo_2();
		run_exo_2(false);
	}
	
	public static void init_exo_2() {
		double step = 0.001;
		
		Output y_o0 = new Output("Stepper_0_y(out)");
		Input y_i0 = new Input("Adder_y0(in)", y_o0);
		
		Output y_o1 = new Output("Stepper_0_y(out)");
		Input y_i1 = new Input("Adder_y1(in)", y_o1);
		
		Output y_o2 = new Output("Stepper_0_y(out)");
		Input y_i2 = new Input("Adder_y2(in)", y_o2);
		
		Output y_o3 = new Output("Stepper_0_y(out)");
		Input y_i3 = new Input("Adder_y3(in)", y_o3);
		
		Output s_o0 = new Output("Adder_i0(out)");
		Input s_i0 = new Input("Integrator_0_s0(in)", s_o0);
		
		Output sp_o0 = new Output("Integrator_0_s0(out)");
		Input sp_i0 = new Input("Integrator_1_s0(in)", sp_o0);
		
		Stepper stepper_0 = new Stepper("Stepper_0", 1.0, -3.0, 0.65);
		stepper_0.addOutput(y_o0);
		
		Stepper stepper_1 = new Stepper("Stepper_1", 0.0, 1.0, 0.35);
		stepper_1.addOutput(y_o1);
		
		Stepper stepper_2 = new Stepper("Stepper_2", 0.0, 1.0, 1.0);
		stepper_2.addOutput(y_o2);
		
		Stepper stepper_3 = new Stepper("Stepper_3", 0.0, 4.0, 1.5);
		stepper_3.addOutput(y_o3);
		
		Adder adder = new Adder();
		adder.addInput(y_i0);
		adder.addInput(y_i1);
		adder.addInput(y_i2);
		adder.addInput(y_i3);
		adder.addOutput(s_o0);
		
		Integrator_TD integrator_0 = new Integrator_TD(step);
		integrator_0.addInput(s_i0);
		integrator_0.addOutput(sp_o0);
		
		Integrator_TD integrator_1 = new Integrator_TD(step);
		integrator_1.addInput(sp_i0);
		
		components.add(stepper_0);
		components.add(stepper_1);
		components.add(stepper_2);
		components.add(stepper_3);
		components.add(adder);
		components.add(integrator_0);
		components.add(integrator_1);
	}
	
	public static void run_exo_2(boolean show) {
		ChartFrame chartframe_2 = new ChartFrame("Resultat Exercice 2(Temps discret)", "Stepper > Adder > Integrator > Integrator");
		Chart cDeri = new Chart("Derivate");
		Chart cInte = new Chart("Integrale");
		Chart cInte2 = new Chart("Double_Integrale");
		chartframe_2.addToLineChartPane(cDeri);
		chartframe_2.addToLineChartPane(cInte);
		chartframe_2.addToLineChartPane(cInte2);
		while(t <= tf) {
			cDeri.addDataToSeries(t, (double)components.get(4).getValue());
			cInte.addDataToSeries(t, (double)components.get(5).getValue());
			cInte2.addDataToSeries(t, (double)components.get(6).getValue());
			getTr_min();
			updateImms();
			stepTime();
			updateOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void exo_1() {
		components = new ArrayList<Component>();
		tf = 20.0;
		init_exo_1();
		run_exo_1(false);
	}
	
	public static void init_exo_1() {
		Output job_o0 = new Output("Gen_job(out)", 0);
		Input job_i0 = new Input("Buf_job(in)", job_o0);
		
		Output req_o0 = new Output("Buf_req(out)", 1);
		Input req_i0 = new Input("Proc_req(in)", req_o0);
		
		Output done_o0 = new Output("Proc_done(out)", 1);
		Input done_i0 = new Input("Buf_done(in)", done_o0);
		
		Generator generator = new Generator();
		generator.addOutput(job_o0);
		
		Buffer buffer = new Buffer();
		buffer.addInput(job_i0);
		buffer.addInput(done_i0);
		buffer.addOutput(req_o0);
		
		Processor processor = new Processor();
		processor.addInput(req_i0);
		processor.addOutput(done_o0);
		
		components.add(generator);
		components.add(buffer);
		components.add(processor);
	}
	
	public static void run_exo_1(boolean show) {
		ChartFrame chartframe_1 = new ChartFrame("Resultat Exercice 1", "Generator > Buffer >< Processor");
		Chart cQ = new Chart("q");
		chartframe_1.addToLineChartPane(cQ);
		while(t <= tf) {
			cQ.addDataToSeries(t, (int)components.get(1).getValue());
			getTr_min();
			updateImms();
			stepTime();
			updateOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void stepTime() {
		t += tr_min;
	}
	
	public static void getTr_min() {
		tr_min = Double.POSITIVE_INFINITY;
		for(Component comp : components) {
			if(comp.getTr() < tr_min)
				tr_min = comp.getTr();
		}
		if(tr_min == Double.POSITIVE_INFINITY)
			tr_min = 0.1;
	}
	
	public static void updateImms(){
		imms = null;
		for(Component comp : components) {
			if(comp.getTr() == tr_min) {
				if(imms == null)
					imms = new ArrayList<Component>();
				imms.add(comp);
			}
		}
	}
	
	public static void updateE() {
		for(Component comp : components)
			comp.setE(comp.getE() + tr_min);
	}
	
	public static void updateTr() {
		for(Component comp : components)
			comp.setTr(comp.getTr() - tr_min);
	}
	
	public static void lambdaCalls() {
		if(imms != null) {
			for(Component comp : imms)
				comp.lambda();
		}
	}
	
	public static void updateIns() {
		for(Component comp : components)
			comp.setIns();
	}
	
	public static void updateOuts() {
		for(Component comp : components)
			comp.setOuts();
	}
	
	public static void refresh() {
		if(imms != null) {
			for(Component comp : components) {
				if(imms.contains(comp) && comp.getIns() == null) {
					comp.internal();
					comp.updateTimers(t);
				}else if(!imms.contains(comp) && comp.getIns() != null) {
					comp.external();
					comp.updateTimers(t);
				}else if(imms.contains(comp) && comp.getIns() != null) {
					if(!comp.external())
						comp.internal();
					comp.updateTimers(t);
				}
			}
		}
	}
	
	public static void showOuts() {
		System.out.println("\n___Next event(s)___");
		for(Component comp : components) {
			if(comp.getOuts() != null) {
				for(Port out : comp.getOuts())
					System.out.printf("%s|%.1f\t", out.getName(), comp.getTr());
				System.out.println("");
			}
		}
	}
	
	public static double getLowestTr(ArrayList<Component> array) {
		double min = Double.POSITIVE_INFINITY;
		for(Component comp : array) {
			if(comp.getTr() < min)
				min = comp.getTr();
		}
		return min;
	}
	
	public static void clearInputs() {
		for(Component comp : components)
			comp.clearInputs();
	}
}
