
public class Camper {
	public int id;
	public int swimtime;
	public int runtime;
	public int biketime;
	public int totaltime;
	public int runBiketime;

	public Camper(int id, int swimtime, int runtime, int biketime) {
		super();
		this.id = id;
		this.swimtime = swimtime;
		this.runtime = runtime;
		this.biketime = biketime;

		this.runBiketime = this.runtime + this.biketime;
		this.totaltime = this.swimtime + this.runBiketime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSwimtime(int time) {
		this.swimtime = time;
	}

	public void setRuntime(int time) {
		this.runtime = time;
	}

	public void setBiketime(int time) {
		this.biketime = time;
	}

	public int getSwimtime() {
		return this.swimtime;
	}

	public int getRuntime() {
		return this.runtime;
	}

	public int getBiketime() {
		return this.biketime;
	}

	public int getId() {
		return this.id;
	}

	public int getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(int totaltime) {
		this.totaltime = totaltime;
	}

	public int getRunBiketime() {
		return runBiketime;
	}

	public void setRunBiketime(int runBiketime) {
		this.runBiketime = runBiketime;
	}
}

