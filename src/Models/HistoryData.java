package Models;

import processing.data.FloatList;

/*
 * This is a class for representing data suitable for a histogram.
 * The color should not really be here, since it's a data model, but it's the most timesaving option at this point.
 */

public class HistoryData {
	
	private String Label;
	private int DataLength;

	private int Sum;
	private float Average;
	private float Min;
	private float Max;
	private int DataColor;
	FloatList History;
	
	public HistoryData(String label, int dataLength, int dataColor) {
		Label = label;
		DataLength = dataLength;
		Sum = 0;
		Average=0;
		Min = 0;
		Max = 0;
		DataColor = dataColor;
		History = new FloatList(DataLength);
		for(int i=0; i<DataLength; i++) {
			History.append(0.0f);
		}
	}
	void push(float value){
		Sum -= History.remove(0);
		Sum += value;
		History.append(value);
		Average = Sum/DataLength;
		Min = History.min();
		Max = History.max();
	}
	
	public void setLabel(String label) {
		Label = label;
	}

	public int getDataLength() {
		return DataLength;
	}

	public void setDataLength(int dataLength) {
		DataLength = dataLength;
	}

	public int getSum() {
		return Sum;
	}

	public float getAverage() {
		return Average;
	}

	public String getLabel() {
		return Label;
	}

	public float getMin() {
		return Min;
	}

	public float getMax() {
		return Max;
	}
	public int getDataColor() {
		return DataColor;
	}
	public void setDataColor(int dataColor) {
		this.DataColor = dataColor;
	}

}
