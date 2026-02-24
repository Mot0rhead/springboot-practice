package com.bolsaideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPagina;
	private int elementosPorPagina;
	private int paginaActual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.paginas= new ArrayList<PageItem>();
		elementosPorPagina= page.getSize();
		totalPagina = page.getTotalPages();
		paginaActual = page.getNumber()+1;
		int desde, hasta;
		if(totalPagina <= elementosPorPagina) {
			desde =1;
			hasta= totalPagina;
		}else {
			if(paginaActual <= elementosPorPagina/2) {
				desde = 1;
				hasta = elementosPorPagina;
			}else if(paginaActual >= totalPagina - elementosPorPagina/2){
				desde = totalPagina - elementosPorPagina +1;
				hasta = elementosPorPagina;
			}else {
				desde = paginaActual - elementosPorPagina/2;
				hasta= elementosPorPagina;
			}
		}
		for(int i =0; i < hasta ;i++) {
			paginas.add(new PageItem(desde+i,paginaActual == desde+i));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPagina() {
		return totalPagina;
	}

	public void setTotalPagina(int totalPagina) {
		this.totalPagina = totalPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	public boolean isFirts() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
}
