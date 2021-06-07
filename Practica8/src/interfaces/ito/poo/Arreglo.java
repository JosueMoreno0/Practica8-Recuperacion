package interfaces.ito.poo;

public interface Arreglo<T> {
	
	public boolean addItem(T item);
	public boolean existeItem(T item);
	public T getItem(int pos);
	public int getSize();  //Retorna cantidad elementos 
	public boolean delete(T item);
	public boolean isFree();
	public boolean isFull();
	
}