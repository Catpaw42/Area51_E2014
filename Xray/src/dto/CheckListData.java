package dto;


public class CheckListData {
	
	public static void main(String[] args) {
		CheckListData t = new CheckListData();
		t.test3();
	}

	public enum HenvistTil{Radiologisk, Klinisk};
	public enum hospitalOenske{Hillerød, Frederikssund};
	public enum prioritering{haste, rutine, fremskyndet, pakkeforloeb};
	private boolean ambulant; 
	public enum ambulantKoersel{ingen, siddende, liggende};
	private boolean indlaeggelse;
	public enum indlaeggelseTransport{gaaMedPortoer, gaaUdenPortoer, koerestol, seng};
	private boolean pregnancy; 
	interface test{
		public void setHenvistTil(HenvistTil value);
		public HenvistTil getHenvistTil();
	}
	public enum S implements test{test1, test2;
	private HenvistTil value;
	@Override
	public void setHenvistTil(HenvistTil value) {
		this.value = value;
		
	}

	@Override
	public HenvistTil getHenvistTil() {
		return value;
	}

	}
	
	public void test3(){
		int a = ambulantKoersel.values().length;
		System.out.println(a);
	}
}
