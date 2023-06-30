/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.listas;

import Utilidades.Utilidades;
import controlador.listas.Excepciones.ListaVaciaException;
import controlador.listas.Excepciones.PosicionNoEncontradaException;
import java.lang.reflect.Array;
import java.util.Comparator;

/**
 *
 * @author Marylin
 */
public class ListasEnlazadas<E> {

    private NodoLista<E> cabecera;
    private Integer tamanio;

    public ListasEnlazadas() {
        cabecera = null;
        tamanio = 0;
    }

    public void insertar(E info) {
        NodoLista<E> nodo = new NodoLista<>(info, null);

        if (isEmpty()) {
            this.cabecera = nodo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);

        }

        tamanio++;
    }

    public void insertarCabecera(E info) {
        if (isEmpty()) {
            insertar(info);
        } else {
            NodoLista<E> nodo = new NodoLista<>(info, null);
            nodo.setSiguiente(cabecera);
            cabecera = nodo;
            tamanio++;
        }
    }

    public void insertarPosicion(E info, Integer pos) throws PosicionNoEncontradaException {
        if (isEmpty()) {
            insertar(info);
        } else if (pos >= 0 && pos < tamanio) {
            if (pos == 0) {
                insertar(info);
            } else {
                NodoLista<E> nodo = new NodoLista<>(info, null);
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < (pos - 1); i++) {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nodo);
                nodo.setSiguiente(siguiente);
                tamanio++;
            }

        } else {
            throw new PosicionNoEncontradaException();
        }
    }

    public void mergeAscendente() {
        E[] arreglo = this.toArray();
        arreglo = OrdenamientoquicksortAscendente(arreglo, 0, arreglo.length - 1);
        this.toList(arreglo);
    }

    public void mergeDescendente() {
        E[] arreglo = this.toArray();

        arreglo = OrdenacionQuicksortDescendente(arreglo, 0, arreglo.length - 1);
        this.toList(arreglo);
    }

    public void imprimir() {
        System.out.println("Lista Enlazada");
        NodoLista<E> aux = cabecera;
        while (aux != null) {
            System.out.println(aux.getInfo().toString() + "\t");
            aux = aux.getSiguiente();
        }
    }

    public E obtener(Integer pos) throws ListaVaciaException, PosicionNoEncontradaException {

        if (!isEmpty()) {
            E info = null;
            if (pos >= 0 && pos < tamanio) {
                if (pos == 0) {
                    info = cabecera.getInfo();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    info = aux.getInfo();
                }
            } else {
                throw new PosicionNoEncontradaException();
            }
            return info;
        } else {
            throw new ListaVaciaException();
        }
    }

    public ListasEnlazadas<E> burbuja() {
        Class<E> clazz = null;
        E[] matriz = toArray();
        if (tamanio > 0) {
            clazz = (Class<E>) cabecera.getInfo().getClass();
            Boolean isObject = Utilidades.isObject(clazz);
            if (isObject) {
            } else {
                for (int i = matriz.length; i > 1; i--) {
                    for (int j = 0; j < i - 1; j++) {
                        E auxJ = matriz[j];
                        E auxJ1 = matriz[j + 1];
                        if (Utilidades.isNumber(clazz)) {
                            if (((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue()) {
                                matriz[j] = auxJ1;
                                matriz[j + 1] = auxJ;
                            }
                        }
                        if (Utilidades.isString(clazz)) {
                            if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) > 0) {
                                matriz[j] = auxJ1;
                                matriz[j + 1] = auxJ;
                            }
                        }
                    }
                }
            }
        }
        if (matriz != null) {
            toList(matriz);
        }
        return this;
    }

    public E eliminarPosicion(Integer pos) throws ListaVaciaException, PosicionNoEncontradaException {
        if (!isEmpty()) {
            E info = null;
            if (pos >= 0 && pos < tamanio) {
                if (pos == 0) {
                    info = cabecera.getInfo();
                    cabecera = cabecera.getSiguiente();
                    this.tamanio--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    info = aux.getInfo();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    tamanio--;
                }
            } else {
                throw new PosicionNoEncontradaException();
            }
            return info;
        } else {
            throw new ListaVaciaException();
        }
    }

    public ListasEnlazadas<E> llenarListaDatos(Integer cantidadDato, Class<E> tipoInfo) {
        vaciar();
        Class<E> clazz = (Class<E>) tipoInfo;

        if (Utilidades.isNumber(clazz)) {
            if (tipoInfo.getSimpleName().equalsIgnoreCase("Float")) {
                if (this.getTamanio() < cantidadDato) {
                    for (int i = 0; i < cantidadDato; i++) {
                        E random = (E) Float.valueOf((float) Math.random());
                        this.insertar(random);
                    }
                }
            }

            if (tipoInfo.getSimpleName().equalsIgnoreCase("Integer")) {
                if (this.getTamanio() < cantidadDato) {
                    for (int i = 0; i < cantidadDato; i++) {
                        E random = (E) Integer.valueOf((int) (Math.random() * 100));
                        this.insertar(random);
                    }
                }
            }

        }
        if (Utilidades.isString(clazz)) {
            String serie = "TUSMETASsonmasGRANDESquetusMIEDOS";
            String random = "";
            for (int j = 0; j < cantidadDato; j++) {
                for (int i = 0; i < 8; i++) {
                    int max = 8;
                    int min = 1;
                    int rango = max - min + 1;
                    int indice = (int) (Math.random() * rango) + min;
                    char rand = serie.charAt(indice);
                    random = random + rand;
                }
                this.insertar((E) random);
                random = "";
            }

        }
        return this;
    }

    public void quicksortAscendente() {
        E[] arreglo = this.toArray();
        arreglo = OrdenamientoquicksortAscendente(arreglo, 0, arreglo.length - 1);
        this.toList(arreglo);
    }

    public void quicksortDescendente() {
        E[] arreglo = this.toArray();
        arreglo = OrdenacionQuicksortDescendente(arreglo, 0, arreglo.length - 1);
        this.toList(arreglo);
    }

    private E[] OrdenamientoquicksortAscendente(E[] arreglo, int primero, int ultimo) {
        Class<E> clazz = null;
        int objeto1 = primero;
        int objeto2 = ultimo;
        E pivote = arreglo[(primero + ultimo) / 2];
        if (tamanio > 0) {
            clazz = (Class<E>) cabecera.getInfo().getClass();
            Boolean isObject = Utilidades.isObject(clazz);
            if (isObject) {
            } else {
                if (Utilidades.isNumber(clazz)) {
                    while (objeto1 <= objeto2) {
                        while (((Number) arreglo[objeto1]).doubleValue() < ((Number) pivote).doubleValue()) {
                            objeto1 = objeto1 + 1;
                        }
                        while (((Number) arreglo[objeto2]).doubleValue() > ((Number) pivote).doubleValue()) {
                            objeto2 = objeto2 - 1;
                        }
                        if (objeto1 <= objeto2) {
                            E aux = arreglo[objeto1];
                            arreglo[objeto1] = arreglo[objeto2];
                            arreglo[objeto2] = aux;
                            objeto1 = objeto1 + 1;
                            objeto2 = objeto2 - 1;
                        }
                    }
                    if (primero < objeto2) {
                        OrdenamientoquicksortAscendente(arreglo, primero, objeto2);
                    }
                    if (objeto1 < ultimo) {
                        OrdenamientoquicksortAscendente(arreglo, objeto1, ultimo);
                    }
                }
                if (Utilidades.isString(clazz)) {
                    while (objeto1 <= objeto2) {
                        while (arreglo[objeto1].toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) > 0) {
                            objeto1 = objeto1 + 1;
                        }
                        while (arreglo[objeto2].toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) < 0) {
                            objeto2 = objeto2 - 1;
                        }
                        if (objeto1 <= objeto2) {
                            E aux = arreglo[objeto1];
                            arreglo[objeto1] = arreglo[objeto2];
                            arreglo[objeto2] = aux;
                            objeto1 = objeto1 + 1;
                            objeto2 = objeto2 - 1;
                        }
                    }

                    if (primero < objeto2) {
                        OrdenamientoquicksortAscendente(arreglo, primero, objeto2);
                    }
                    if (objeto1 < ultimo) {
                        OrdenamientoquicksortAscendente(arreglo, objeto1, ultimo);
                    }
                }

            }

        }
        return arreglo;
    }

    private E[] OrdenacionQuicksortDescendente(E[] arreglo, int primero, int ultimo) {
        Class<E> clazz = null;
        int objecto1 = primero;
        int objecto2 = ultimo;
        E pivote = arreglo[(primero + ultimo) / 2];
        if (tamanio > 0) {
            clazz = (Class<E>) cabecera.getInfo().getClass();
            Boolean isObject = Utilidades.isObject(clazz);
            if (isObject) {
            } else {
                if (Utilidades.isNumber(clazz)) {
                    while (objecto1 <= objecto2) {
                        while (((Number) arreglo[objecto1]).doubleValue() > ((Number) pivote).doubleValue()) {
                            objecto1 = objecto1 + 1;
                        }
                        while (((Number) arreglo[objecto2]).doubleValue() < ((Number) pivote).doubleValue()) {
                            objecto2 = objecto2 - 1;
                        }
                        if (objecto1 <= objecto2) {
                            E aux = arreglo[objecto1];
                            arreglo[objecto1] = arreglo[objecto2];
                            arreglo[objecto2] = aux;
                            objecto1 = objecto1 + 1;
                            objecto2 = objecto2 - 1;
                        }
                    }

                    if (primero < objecto2) {
                        OrdenacionQuicksortDescendente(arreglo, primero, objecto2);
                    }
                    if (objecto1 < ultimo) {
                        OrdenacionQuicksortDescendente(arreglo, objecto1, ultimo);
                    }
                }
                if (Utilidades.isString(clazz)) {
                    while (objecto1 <= objecto2) {
                        while (arreglo[objecto1].toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) < 0) {
                            objecto1 = objecto1 + 1;
                        }
                        while (arreglo[objecto2].toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) > 0) {
                            objecto2 = objecto2 - 1;
                        }
                        if (objecto1 <= objecto2) {
                            E aux = arreglo[objecto1];
                            arreglo[objecto1] = arreglo[objecto2];
                            arreglo[objecto2] = aux;
                            objecto1 = objecto1 + 1;
                            objecto2 = objecto2 - 1;
                        }
                    }

                    if (primero < objecto2) {
                        OrdenacionQuicksortDescendente(arreglo, primero, objecto2);
                    }
                    if (objecto1 < ultimo) {
                        OrdenacionQuicksortDescendente(arreglo, objecto1, ultimo);
                    }
                }

            }
        }
        return arreglo;
    }

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getTamanio() {

        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

//    public static void mergesort(int A[], int izq, int der) {
//        if (izq < der) {
//            int m = (izq + der) / 2;
//            mergesort(A, izq, m);
//            mergesort(A, m + 1, der);
//            merge(A, izq, m, der);
//        }
//    }
//    public static void mergesort(Elemento[] arr, int izq, int der) {
//        if (izq < der) {
//            int m = (izq + der) / 2;
//            mergesort(arr, izq, m);
//            mergesort(arr, m + 1, der);
//            merge(arr, izq, m, der);
//        }
//    }
//    public static void mergesort(ListasEnlazadas<Object> lista, int izq, int der) {
//        if (izq < der) {
//            int m = (izq + der) / 2;
//            mergesort(lista, izq, m);
//            mergesort(lista, m + 1, der);
//            merge(lista, izq, m, der);
//        }
//    }
//    public static void merge(int A[], int izq, int m, int der) {
//        int i, j, k;
//        int[] B = new int[A.length]; //array auxiliar
//        for (i = izq; i <= der; i++) //copia ambas mitades en el array auxiliar                                       
//        {
//            B[i] = A[i];
//        }
//
//        i = izq;
//        j = m + 1;
//        k = izq;
//
//        while (i <= m && j <= der) //copia el siguiente elemento más grande                                      
//        {
//            if (B[i] <= B[j]) {
//                A[k++] = B[i++];
//            } else {
//                A[k++] = B[j++];
//            }
//        }
//
//        while (i <= m) //copia los elementos que quedan de la
//        {
//            A[k++] = B[i++]; //primera mitad (si los hay)
//        }
//    }
//    public static void merge(ListasEnlazadas<Object> lista, int izq, int m, int der) {
//        int i, j, k;
//        ListasEnlazadas<Object> aux = new ListasEnlazadas<>(); // lista auxiliar
//
//        for (i = izq; i <= der; i++) {
//            aux.insertar(lista.obtener(i));
//        }
//
//        i = izq;
//        j = m + 1;
//        k = izq;
//
//        while (i <= m && j <= der) {
//            if (aux.obtener(i - izq)(aux.obtener(j - izq)) <= 0) {
//                lista.set(k++, aux.obtener(i++ - izq));
//            } else {
//                lista.set(k++, aux.obtener(j++ - izq));
//            }
//        }
//
//        while (i <= m) {
//            lista.set(k++, aux.obtener(i++ - izq));
//        }
//    }
    public void mergesort(Object[] matriz, int izq, int der) {
        //Object[] matriz = this.toArray();
        if (izq < der) {
            int m = (izq + der) / 2;
            mergesort(matriz, izq, m);
            mergesort(matriz, m + 1, der);
            merge(matriz, izq, m, der);
        }
        this.toList((E[]) matriz);
    }

    public static void merge(Object[] a, int izq, int m, int der) {

        int i, j, k;
        Object[] b = new Object[a.length]; // arreglo auxiliar

        for (i = izq; i <= der; i++) {
            b[i] = a[i];
        }

        i = izq;
        j = m + 1;
        k = izq;

        while (i <= m && j <= der) {
            if (a[0].getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
                if (((Number) b[i]).doubleValue() >= ((Number) b[j]).doubleValue()) {
                    a[k++] = b[i++];
                } else {
                    a[k++] = b[j++];
                }
            }
            /*if (aux[j].toString().compareTo(aux[i].toString()) <= 0) {
                lista.toArray()[k++] = aux[i++];
            } else {
                lista.toArray()[k++] = aux[j++];
            }*/
        }

        while (i <= m) {
            a[k++] = b[i++];
        }
    }

    public E[] toArray() {

        E[] matriz = null;
        if (this.tamanio > 0) {
            matriz = (E[]) Array.newInstance(cabecera.getInfo().getClass(), this.tamanio);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.tamanio; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getSiguiente();
            }
        }
        return matriz;
    }

    public ListasEnlazadas<E> toList(E[] array) {
        this.vaciar();
        for (int i = 0; i < array.length; i++) {
            insertar(array[i]);
        }
        return this;
    }

    public void vaciar() {
        this.cabecera = null;
        setTamanio(0);
    }

    public Boolean isEmpty() {
        return cabecera == null;
    }

}
